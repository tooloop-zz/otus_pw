package ru.otus.asamofalov.pw.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class GetResponse {

    private UUID uuid;

    private String name;

    private double sum;

    private CreditRequestState state;

    private double percentageRate;

    public static GetResponse fromCreditRequest(CreditRequest creditRequest) {
        GetResponse getResponse = new GetResponse();
        getResponse.setState(creditRequest.getState());
        getResponse.setUuid(creditRequest.getUuid());
        getResponse.setName(creditRequest.getName());
        getResponse.setSum(creditRequest.getSum());
        getResponse.setPercentageRate(Math.floor(creditRequest.getPercentageRate() * 100) / 100);
        return getResponse;
    }

}
