package com.realestateproject.service;

import com.realestateproject.payload.AgentDto;
import java.util.List;

public interface AgentService {
    AgentDto saveAgent(AgentDto agentDto);
    List<AgentDto> getAllAgents();
    AgentDto getAgentById(Long id);
    void deleteAgent(Long id);
    AgentDto updateAgent(Long id, AgentDto agentDto);
}