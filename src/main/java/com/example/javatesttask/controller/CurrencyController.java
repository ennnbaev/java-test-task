package com.example.javatesttask.controller;

import com.example.javatesttask.model.Currency;
import com.example.javatesttask.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/currency")
@Tag(name = "Currency")
public record CurrencyController(CurrencyService privatBankCurrencyService,
                                 CurrencyService monoBankCurrencyService,
                                 CurrencyService minfinCurrencyService) {

    @PostMapping
    @Operation(summary = "Refreshes currency rates data in the database")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @ResponseStatus(HttpStatus.CREATED)
    public String refreshCurrencyRates() {
        Thread minfinRateRefreshTask = new Thread(minfinCurrencyService::saveCurrencyRates);
        Thread privatRateRefreshTask = new Thread(privatBankCurrencyService::saveCurrencyRates);
        Thread monoRateRefreshTask = new Thread(monoBankCurrencyService::saveCurrencyRates);
        List.of(privatRateRefreshTask, monoRateRefreshTask, minfinRateRefreshTask).forEach(Thread::start);

        return "Data was saved successfully!";
    }

    @GetMapping
    @Operation(summary = "Get currency data from database")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getCurrencyDataByDateRange(
            @Parameter @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return privatBankCurrencyService.getCurrencyDataByDateRange(startDate, endDate);
    }
}