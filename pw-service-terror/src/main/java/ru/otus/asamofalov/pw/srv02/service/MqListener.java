package ru.otus.asamofalov.pw.srv02.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.otus.asamofalov.pw.Constants;
import ru.otus.asamofalov.pw.domain.CreditRequest;
import ru.otus.asamofalov.pw.domain.CreditRequestState;
import ru.otus.asamofalov.pw.helper.Tools;
import ru.otus.asamofalov.pw.srv02.repository.TerroristsRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqListener {

    private final ObjectMapper objectMapper;
    private final TerroristsRepository terroristsRepository;
    private final Channel channel;

    @RabbitListener(queues = Constants.SERVICE_TERROR_REQUEST_QUEUE)
    public void processMessages(byte[] message) throws IOException {
        log.debug("new request received");
        CreditRequest creditRequest = objectMapper.readValue(message, CreditRequest.class);
        log.trace("request data {}", creditRequest);
        if (terroristsRepository.existsTerroristByDocSeriesAndDocNumberOrName(
                creditRequest.getDocSeries(),
                creditRequest.getDocNumber(),
                creditRequest.getName()
        )) {
            creditRequest.setState(CreditRequestState.DECLINED_BY_TERROR_CHECK);
            log.warn("client found in terrorists DB");
        } else {
            creditRequest.setState(CreditRequestState.TERRORIST_CHECKED);
            log.trace("client by terrorists DB checked");
        }
        channel.basicPublish("", Constants.SERVICE_TERROR_RESPONSE_QUEUE, null, objectMapper.writeValueAsBytes(creditRequest));
        log.debug("request processed{}", Tools.getTraceDetails(creditRequest));
    }
}
