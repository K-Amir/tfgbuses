package com.busbooking.ErrorHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorOutputDto {
    private int httpCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime fecha;
    private String msgError;



    private ErrorOutputDto() {
        fecha = LocalDateTime.now();
    }

    ErrorOutputDto(int status) {
        this();
        this.httpCode = status;
    }

    ErrorOutputDto(int status, Throwable ex) {
        this();
        this.httpCode = status;
        this.msgError = "Unexpected error";

    }

    ErrorOutputDto(int status, String message, Throwable ex) {
        this();
        this.httpCode = status;
        this.msgError = message;

    }
}
