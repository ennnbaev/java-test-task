package com.example.javatesttask.service.impl;

import com.example.javatesttask.dao.CurrencyRepository;
import com.example.javatesttask.model.Currency;
import com.example.javatesttask.service.request.MonoRequest;
import com.example.javatesttask.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@PropertySource("classpath:application.properties")
public record MonoBankCurrencyService(
        @Value("${api.external.mono}") String monoUrl,
        RestTemplate restTemplate,
        CurrencyRepository currencyRepository) implements CurrencyService {

    @Override
    public void saveCurrencyRates() {
        currencyRepository.saveAll(getCurrencyRates());
    }

    private List<Currency> getCurrencyRates() {
        MonoRequest[] monoData = Optional.ofNullable(restTemplate.getForObject(monoUrl, MonoRequest[].class))
                .orElseThrow(RuntimeException::new);
        return Arrays.stream(monoData)
                .map(monoDatum -> new Currency(monoDatum.currencyCodeA(), monoDatum.currencyCodeB(), monoDatum.rateBuy(), monoDatum.rateSell()))
                .toList();
    }

    @Override
    public List<Currency> getCurrencyDataByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return currencyRepository.findByDateBetween(startDate, endDate);
    }

}
