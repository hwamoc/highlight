package com.liner.example.response;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseMessage {

    private Integer code;

    private String status;

    private String message;

    private Object data;

}