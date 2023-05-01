package com.example.javatesttask.service.impl;

import com.example.javatesttask.dao.CurrencyRepository;
import com.example.javatesttask.model.Currency;
import com.example.javatesttask.service.CurrencyService;

import com.example.javatesttask.service.request.MinfinRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@PropertySource("classpath:application.properties")
public record MinfinCurrencyService(@Value("${api.external.minfin}") String monoUrl,
                                    RestTemplate restTemplate,
                                    CurrencyRepository currencyRepository) implements CurrencyService {

    @Override
    public void saveCurrencyRates() {
        try {
            currencyRepository.saveAll(getCurrencyRates());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Currency> getCurrencyRates() throws JsonProcessingException {
        String json = restTemplate.getForObject(monoUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, MinfinRequest> currencies = objectMapper.readValue(json, new TypeReference<>() {
        });
        return currencies.entrySet().stream()
                .map(entry -> new Currency(entry.getKey(), "ua", entry.getValue().ask(), entry.getValue().bid()))
                .toList();
    }

    @Override
    public List<Currency> getCurrencyDataByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return currencyRepository.findByDateBetween(startDate, endDate);
    }

}
