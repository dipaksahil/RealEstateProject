package com.realestateproject.exception;

public class AgentNotFoundException extends RuntimeException {

        public AgentNotFoundException(Long id) {
            super("Agent not found with id " + id);
        }
    }