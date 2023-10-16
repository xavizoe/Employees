package com.ironbit.test.crud.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@ApiModel("ErrorPayload")
public class ErrorPayload {

    @ApiModelProperty(value = "mesage", example = "No employees found")
    private String message;

    @ApiModelProperty(value = "status", example = "404")
    private HttpStatus status;

}
