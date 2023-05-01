package com.example.javatesttask.service;

import com.example.javatesttask.model.Currency;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CurrencyService {

    void saveCurrencyRates();

    List<Currency> getCurrencyDataByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Scheduled(cron = "0 * * * * *")
    default void schedulingRequestForCurrencyRates() {
        saveCurrencyRates();
    }

}
