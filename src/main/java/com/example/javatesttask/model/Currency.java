package com.example.javatesttask.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ask_name")
    private String askName;
    @Column(name = "bid_name")
    private String bidName;
    @Column(name = "rate_buy")
    private double rateAsk;
    @Column(name = "rate_sell")
    private double rateBid;
    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDateTime date;

    public Currency() {

    }

    public Currency(String askName, String bidName, double rateAsk, double rateBid) {
        this.askName = askName;
        this.bidName = bidName;
        this.rateAsk = rateAsk;
        this.rateBid = rateBid;
        this.date = LocalDateTime.now();
    }

    public String getAskName() {
        return askName;
    }

    public void setAskName(String currencyAsk) {
        this.askName = currencyAsk;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String currencyBid) {
        this.bidName = currencyBid;
    }

    public double getRateAsk() {
        return rateAsk;
    }

    public void setRateAsk(double rateBuy) {
        this.rateAsk = rateBuy;
    }

    public double getRateBid() {
        return rateBid;
    }

    public void setRateBid(double rateSell) {
        this.rateBid = rateSell;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

