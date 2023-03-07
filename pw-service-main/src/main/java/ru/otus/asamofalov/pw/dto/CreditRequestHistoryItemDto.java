package ru.otus.asamofalov.pw.dto;

import lombok.Data;
import ru.otus.asamofalov.pw.domain.CreditRequestHistoryItem;
import ru.otus.asamofalov.pw.domain.CreditRequestState;

import java.text.SimpleDateFormat;

@Data
public class CreditRequestHistoryItemDto {

    private long id;

    private CreditRequestState state;

    private String updated;

    public static CreditRequestHistoryItemDto fromDomainObject(CreditRequestHistoryItem item) {
        CreditRequestHistoryItemDto dto = new CreditRequestHistoryItemDto();
        dto.setId(item.getId());
        dto.setState(item.getState());
        dto.setUpdated(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(item.getUpdated()));
        return dto;
    }
}
