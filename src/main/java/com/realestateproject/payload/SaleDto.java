package com.realestateproject.payload;

import lombok.Data;
import java.time.LocalDateTime;
// Sale DTO
@Data
public class SaleDto {
    private Long saleId;
    //private Long agentId;
    private LocalDateTime saleDate;
    private Long propertyId;
    private ClientRegDto clientReg;
    private AgentDto agent;
    //Constructor to set the saleDate field to the current date and time
    public SaleDto() {
        this.saleDate = LocalDateTime.now();
    }
}