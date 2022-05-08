package com.busbooking.ErrorHandling;

import lombok.*;
import org.springframework.http.ResponseEntity;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SuccessDto {
    private String message;
    private int statusCode = 200;
    public static ResponseEntity<SuccessDto> send(String message){
        return ResponseEntity.ok().body(new SuccessDto(message,200));
    }
}
