package com.ironbit.test.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Bitacora implements Serializable {

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("data")
    private String data;

}
