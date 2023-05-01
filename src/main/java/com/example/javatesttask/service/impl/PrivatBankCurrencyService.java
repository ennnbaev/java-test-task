package com.example.javatesttask.service.impl;

import com.example.javatesttask.dao.CurrencyRepository;
import com.example.javatesttask.model.Currency;
import com.example.javatesttask.service.request.PrivatRequest;
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
public record PrivatBankCurrencyService(@Value("${api.external.privat}") String monoUrl,
                                        RestTemplate restTemplate,
                                        CurrencyRepository currencyRepository) implements CurrencyService {

    @Override
    public void saveCurrencyRates() {
        currencyRepository.saveAll(getCurrencyRates());
    }

    private List<Currency> getCurrencyRates() {
        PrivatRequest[] privatData = Optional.ofNullable(restTemplate.getForObject(monoUrl, PrivatRequest[].class))
                .orElseThrow(RuntimeException::new);

        return Arrays.stream(privatData)
                .map(privatDatum -> new Currency(privatDatum.ccy(), privatDatum.baseCcy(), privatDatum.buy(), privatDatum.sale()))
                .toList();
    }

    @Override
    public List<Currency> getCurrencyDataByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return currencyRepository.findByDateBetween(startDate, endDate);
    }
}
