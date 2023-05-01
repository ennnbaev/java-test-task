package com.example.javatesttask.dao;

import com.example.javatesttask.model.Currency;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
