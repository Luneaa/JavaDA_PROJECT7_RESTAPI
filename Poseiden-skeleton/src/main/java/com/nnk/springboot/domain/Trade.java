package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "trade")
public class Trade {
    @Id
    private Integer tradeId;

    private String account;

    private String type;

    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private Timestamp tradeDate;

    private String security;

    private String status;

    private String trader;

    private String benchmark;

    private String book;

    private String creationName;

    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    public Trade() {

    }
}
