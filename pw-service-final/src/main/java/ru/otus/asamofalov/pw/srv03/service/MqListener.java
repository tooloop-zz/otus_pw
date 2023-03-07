package ru.otus.asamofalov.pw.srv03.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.asamofalov.pw.Constants;
import ru.otus.asamofalov.pw.domain.CreditRequest;
import ru.otus.asamofalov.pw.domain.CreditRequestState;
import ru.otus.asamofalov.pw.helper.Tools;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqListener {

    @Value("${percentage_rate_min}")
    private Integer percentageRateMin;

    @Value("${percentage_rate_max}")
    private Integer percentageRateMax;

    private final ObjectMapper objectMapper;
    private final Channel channel;

    @RabbitListener(queues = Constants.SERVICE_FINAL_REQUEST_QUEUE)
    public void processMessages(byte[] message) throws IOException {
        log.debug("new request received");
        CreditRequest creditRequest = objectMapper.readValue(message, CreditRequest.class);
        log.trace("request data {}", creditRequest);

        if (Math.random() <= 0.75) {
            creditRequest.setState(CreditRequestState.FINAL_APPROVED);
            creditRequest.setPercentageRate(percentageRateMin + Math.random() * (percentageRateMax - percentageRateMin));
            log.warn("final randomly approved with percentage rate {}", creditRequest.getPercentageRate());
        } else {
            creditRequest.setState(CreditRequestState.FINAL_DECLINED);
            log.trace("final randomly declined");
        }
        channel.basicPublish("", Constants.SERVICE_FINAL_RESPONSE_QUEUE, null, objectMapper.writeValueAsBytes(creditRequest));
        log.debug("request processed{}", Tools.getTraceDetails(creditRequest));
    }
}
