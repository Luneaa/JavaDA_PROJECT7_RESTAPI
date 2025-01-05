package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Moddys rating is mandatory")
    @Size(max = 125, message = "Moodys rating is too long")
    private String moodysRating;

    @NotBlank(message = "SandP rating is mandatory")
    @Size(max = 125, message = "SandP rating is too long")
    private String sandPRating;

    @NotBlank(message = "Fitch rating is mandatory")
    @Size(max = 125, message = "Fitch rating is too long")
    private String fitchRating;

    @NotNull(message = "Order number is mandatory")
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating() {

    }
}
