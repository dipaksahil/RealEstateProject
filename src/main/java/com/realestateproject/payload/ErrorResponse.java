package com.realestateproject.payload;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String details;
    private String timestamp;

    public ErrorResponse(int status, String exMessage, String details, String timestamp) {
        this.status = status;
        this.message = exMessage;
        this.details = details;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}