package com.example.javatesttask.service.request;

public record MonoRequest(String currencyCodeA,
                          String currencyCodeB,
                          int date,
                          double rateBuy,
                          double rateSell) {
}
