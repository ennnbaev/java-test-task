package com.example.javatesttask.service.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MinfinRequest(double ask, double bid) {

}
