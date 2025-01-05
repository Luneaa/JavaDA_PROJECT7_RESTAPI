package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 125, message = "Username is too long")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(max = 125, message = "Password is too long")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    @Size(max = 125, message = "Full name is too long")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Size(max = 125, message = "Role is too long")
    private String role;

}
