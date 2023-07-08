package com.realestateproject.exception;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long id) {
        super("Sale not found with clientId : " + id);
    }
}