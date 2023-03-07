package ru.otus.asamofalov.pw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.asamofalov.pw.Constants;
import ru.otus.asamofalov.pw.domain.CreditRequest;
import ru.otus.asamofalov.pw.domain.CreditRequestState;
import ru.otus.asamofalov.pw.helper.Tools;
import ru.otus.asamofalov.pw.repository.CreditRequestsRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinalListener {

    private final ObjectMapper objectMapper;
    private final CreditRequestsRepository creditRequestsRepository;
    private final Channel channel;

    @RabbitListener(queues = Constants.SERVICE_FINAL_RESPONSE_QUEUE)
    @Transactional
    public void processMessages(byte[] message) throws IOException {

        log.debug("new request from final check received");
        CreditRequest processed = objectMapper.readValue(message, CreditRequest.class);
        log.trace("request data {}", processed);
        Optional<CreditRequest> optionalCreditRequest = creditRequestsRepository.findByUuid(processed.getUuid());
        if (!optionalCreditRequest.isPresent()) {
            log.debug("credit request with {} not found, skipped", processed.getUuid());
            return;
        }

        CreditRequest creditRequest = optionalCreditRequest.get();
        if (processed.getState().equals(CreditRequestState.FINAL_APPROVED)) {
            creditRequest.setPercentageRate(processed.getPercentageRate());
        }
        creditRequest.setState(processed.getState());
        creditRequestsRepository.save(creditRequest);

        log.debug("request processed{}", Tools.getTraceDetails(creditRequest));
    }
}
