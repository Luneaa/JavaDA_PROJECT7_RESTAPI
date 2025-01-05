package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue
    private Integer tradeId;

    @NotBlank(message = "Account name is mandatory")
    @Size(max = 30, message = "Account name is too long")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "Type is too long")
    private String type;

    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    @PastOrPresent(message = "Trade date cannot be in the future")
    private Timestamp tradeDate;

    @Size(max = 125, message = "Security is too long")
    private String security;

    @Size(max = 10, message = "Status is too long")
    private String status;

    @Size(max = 125, message = "Trader is too long")
    private String trader;

    @Size(max = 125, message = "Benchmark is too long")
    private String benchmark;

    @Size(max = 125, message = "Book is too long")
    private String book;

    @Size(max = 125, message = "Creation name is too long")
    private String creationName;

    @PastOrPresent(message = "Creation date cannot be in the future")
    private Timestamp creationDate;

    @Size(max = 125, message = "Revision name is too long")
    private String revisionName;

    @PastOrPresent(message = "Revision date cannot be in the future")
    private Timestamp revisionDate;

    @Size(max = 125, message = "Deal name is too long")
    private String dealName;

    @Size(max = 125, message = "Deal type is too long")
    private String dealType;

    @Size(max = 125, message = "Source list Id is too long")
    private String sourceListId;

    @Size(max = 125, message = "Side is too long")
    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    public Trade() {

    }
}
