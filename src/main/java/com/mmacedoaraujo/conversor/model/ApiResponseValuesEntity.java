package com.mmacedoaraujo.conversor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseValuesEntity {
    @JsonProperty("code")
    private String code;
    @JsonProperty("codein")
    private String codein;
    @JsonProperty("name")
    private String name;
    @JsonProperty("bid")
    private Double bid;
}
