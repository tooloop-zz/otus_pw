package ru.otus.asamofalov.pw.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.pw.domain.CreditRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditRequestsRepository extends CrudRepository<CreditRequest, Long> {
    Optional<CreditRequest> findByUuid(UUID uuid);

    List<CreditRequest> findAll();
}
