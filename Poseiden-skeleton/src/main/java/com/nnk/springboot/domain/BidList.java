package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30, message = "Account is too long")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "Type is too long")
    private String type;

    @NotNull(message = "Bid quantity is mandatory")
    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    @Size(max = 125, message = "Benchmark is too long")
    private String benchmark;

    @PastOrPresent(message = "Bid list date cannot be in the future")
    private Timestamp bidListDate;

    @Size(max = 125, message = "Commentary is too long")
    private String commentary;

    @Size(max = 125, message = "Security is too long")
    private String security;

    @Size(max = 10, message = "Status is too long")
    private String status;

    @Size(max = 125, message = "Trader is too long")
    private String trader;

    @Size(max = 125, message = "Book is too long")
    private String book;

    @Size(max = 125, message = "Creation name is too long")
    private String creationName;

    @PastOrPresent(message = "Creation date cannot be in the future")
    private Timestamp creationDate;

    @Size(max = 125, message = "Revision name is too long")
    private String revisionName;

    private Timestamp previsionDate;

    @Size(max = 125, message = "Deal name is too long")
    private String dealName;

    @Size(max = 125, message = "Deal type is too long")
    private String dealType;

    @Size(max = 125, message = "Source list Id is too long")
    private String sourceListId;

    @Size(max = 125, message = "Side is too long")
    private String side;

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public BidList() {

    }
}
