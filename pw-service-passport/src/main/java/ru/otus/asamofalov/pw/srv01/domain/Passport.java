package ru.otus.asamofalov.pw.srv01.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "passports")
public class Passport {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "doc_series")
    private String docSeries;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "doc_date")
    private Date docDate;

}
