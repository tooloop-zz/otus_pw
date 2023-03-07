package ru.otus.asamofalov.pw.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.UUID;

@Data
public class PutRequest {

    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @NotBlank(message = "Необходимо указать серию паспорта")
    @Length(min = 4, max = 4)
    private String docSeries;

    @NotBlank(message = "Необходимо указать номер паспорта")
    @Length(min = 6, max = 6)
    private String docNumber;

    @Past
    private Date docDate;

    @Min(1)
    private double Sum;

    public static CreditRequest toCreditRequest(PutRequest putRequest) {
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setUuid(UUID.randomUUID());
        creditRequest.setName(putRequest.getName());
        creditRequest.setDocSeries(putRequest.getDocSeries());
        creditRequest.setDocNumber(putRequest.getDocNumber());
        creditRequest.setDocDate(putRequest.getDocDate());
        creditRequest.setSum(putRequest.getSum());
        creditRequest.setCreated(new Date());
        return creditRequest;
    }
}
