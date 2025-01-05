package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 125, message = "Name is too long")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 125, message = "Description is too long")
    private String description;

    @NotBlank(message = "Json is mandatory")
    @Size(max = 125, message = "Json is too long")
    private String json;

    @NotBlank(message = "Template is mandatory")
    @Size(max = 125, message = "Template is too long")
    private String template;

    @NotBlank(message = "Sql Str is mandatory")
    @Size(max = 125, message = "Sql Str is too long")
    private String sqlStr;

    @NotBlank(message = "Sql Part is mandatory")
    @Size(max = 125, message = "Sql Part is too long")
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public RuleName() {

    }
}
