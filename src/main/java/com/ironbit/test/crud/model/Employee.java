package com.ironbit.test.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ApiModel("employee")
@ToString
public class Employee implements Serializable {

    @Schema(hidden = true)
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(value = "firstName", name = "firstName", example = "Francisco")
    @JsonProperty("firstName")
    private String firstName;

    @ApiModelProperty(value = "secondName", name = "secondName", example = "Javier")
    @JsonProperty("secondName")
    private String secondName;

    @ApiModelProperty(value = "lastName", name = "lastName", example = "Dominguez")
    @JsonProperty("lastName")
    private String lastName;

    @ApiModelProperty(value = "secondLastName", name = "secondLastName", example = "Gomez")
    @JsonProperty("secondLastName")
    private String secondLastName;

    @ApiModelProperty(value = "age", name = "age", example = "30")
    @JsonProperty("age")
    private String age;

    @ApiModelProperty(value = "sex", name = "sex", example = "MALE")
    @JsonProperty("sex")
    private String sex;

    @ApiModelProperty(value = "birthDate", name = "birthDate", example = "27-12-1992")
    @JsonProperty("birthDate")
    private String birthDate;

    @ApiModelProperty(value = "job", name = "job", example = "Dev")
    @JsonProperty("job")
    private String job;

}
