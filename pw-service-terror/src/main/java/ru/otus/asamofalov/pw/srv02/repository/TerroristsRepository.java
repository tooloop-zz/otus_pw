package ru.otus.asamofalov.pw.srv02.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.pw.srv02.domain.Terrorist;

public interface TerroristsRepository extends CrudRepository<Terrorist, Long> {
    boolean existsTerroristByDocSeriesAndDocNumberOrName(String docSeries, String docNumber, String name);
}

