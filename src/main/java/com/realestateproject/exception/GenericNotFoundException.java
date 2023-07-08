package com.realestateproject.exception;

import org.springframework.http.HttpStatus;

public class GenericNotFoundException extends RuntimeException{
        private HttpStatus httpStatus;

        public GenericNotFoundException(Long id, String entityType, HttpStatus httpStatus) {
            super(entityType + " not found with id : " + id);
            this.httpStatus = httpStatus;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }
