package com.ironbit.test.crud.controller;

import com.ironbit.test.crud.exception.ErrorPayload;
import com.ironbit.test.crud.model.Bitacora;
import com.ironbit.test.crud.model.Employee;
import com.ironbit.test.crud.service.IBitacoraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BitacoraController {
    private final IBitacoraService bitacoraService;

    public BitacoraController(IBitacoraService bitacoraService) {
        this.bitacoraService = bitacoraService;
    }

    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    @ApiOperation(value = "Get Log Activity ",
            produces = "application/json",
            notes = "Get all activity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK.", response = Bitacora.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Data Found",
                    response = ErrorPayload.class)
    })
    @GetMapping("/getBitacora")
    public Mono<ResponseEntity<List<Bitacora>>> getBitacora() throws Exception {

        LOGGER.info(":::Init get bitacora::::");

        return bitacoraService.getBitacora()
                .map(data -> ResponseEntity.status(HttpStatus.OK).body(data));
    }

}
