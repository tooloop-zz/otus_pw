package ru.otus.asamofalov.pw.srv01.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.pw.srv01.domain.Passport;

public interface PassportsRepository extends CrudRepository<Passport, Long> {
    boolean existsPassportByDocSeriesAndDocNumber(String docSeries, String docNumber);
}

