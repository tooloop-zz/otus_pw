package ru.otus.asamofalov.pw.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "credit_requests")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "doc_series")
    private String docSeries;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private Date docDate;

    @Column(name = "sum")
    private double Sum;

    @Column(name = "percentage_rate")
    private double percentageRate;

    @Column(name = "state")
    private CreditRequestState state;

    public void setState(CreditRequestState state) {
        this.state = state;
        this.updated = new Date();
        history.add(new CreditRequestHistoryItem(this.state, this.updated));
    }

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = CreditRequestHistoryItem.class)
    @JoinColumn(name = "credit_request_id", referencedColumnName = "id")
    private List<CreditRequestHistoryItem> history = new ArrayList<>();

}
