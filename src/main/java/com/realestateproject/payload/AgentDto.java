package com.realestateproject.payload;

import lombok.Data;

@Data
public class AgentDto {
    private Long agentId;
    private String agentName;
    private String office;
    private String phoneNumber;
    // Add other fields as necessary...
}
