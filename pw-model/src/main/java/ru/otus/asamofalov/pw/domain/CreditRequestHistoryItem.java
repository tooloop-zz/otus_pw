package ru.otus.asamofalov.pw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "credit_requests_history")
@NoArgsConstructor
public class CreditRequestHistoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "state")
    private CreditRequestState state;

    @Column(name = "updated")
    private Date updated;

    public CreditRequestHistoryItem(CreditRequestState state, Date updated) {
        this.state = state;
        this.updated = updated;
    }
}
