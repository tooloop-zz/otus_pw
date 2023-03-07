package ru.otus.asamofalov.pw.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class PutResponse {

    private UUID uuid;

    private String errors;

    private CreditRequestState state;

    public static PutResponse fromCreditRequest(CreditRequest creditRequest) {
        PutResponse putResponse = new PutResponse();
        putResponse.setState(creditRequest.getState());
        putResponse.setUuid(creditRequest.getUuid());
        return putResponse;
    }
}
