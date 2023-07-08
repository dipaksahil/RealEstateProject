package com.realestateproject.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientRegDto {
    private Long clientId;
    private String clientName;
    private String clientAddress;
    private String phoneNumber;
    private String email;
    private String gender;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}