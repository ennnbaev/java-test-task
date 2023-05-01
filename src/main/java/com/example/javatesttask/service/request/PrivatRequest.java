package com.example.javatesttask.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PrivatRequest(String ccy,
                            @JsonProperty("base_ccy")
                            String baseCcy,
                            double buy,
                            double sale) {
}
