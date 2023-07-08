package com.realestateproject.service.impl;

import com.realestateproject.entities.Agent;
import com.realestateproject.exception.AgentNotFoundException;
import com.realestateproject.payload.AgentDto;
import com.realestateproject.repositories.AgentRepository;
import com.realestateproject.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    private AgentRepository agentRepository;
    private ModelMapper modelMapper;

    public AgentServiceImpl(AgentRepository agentRepository, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
    }

    public AgentDto saveAgent(AgentDto agentDto) {
        Agent agent = modelMapper.map(agentDto, Agent.class);
        agent = agentRepository.save(agent);
        return modelMapper.map(agent, AgentDto.class);
    }

    public List<AgentDto> getAllAgents() {
        return agentRepository.findAll()
                .stream()
                .map(agent -> modelMapper.map(agent, AgentDto.class))
                .collect(Collectors.toList());
    }

    public AgentDto getAgentById(Long id) {
        return agentRepository.findById(id)
                .map(agent -> modelMapper.map(agent, AgentDto.class))
                .orElseThrow(() -> new AgentNotFoundException( id));
    }

    public void deleteAgent(Long id) {
        Agent agent = agentRepository.findById(id).orElseThrow(
                () -> new AgentNotFoundException(id));
        agentRepository.deleteById(id);
    }

    public AgentDto updateAgent(Long id, AgentDto agentDto) {
        Agent agent = agentRepository.findById(id).orElseThrow(
                () -> new AgentNotFoundException(id));
        // Update fields of agent...
        agent.setAgentName(agentDto.getAgentName());
        agent.setOffice(agentDto.getOffice());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        Agent savedAgent = agentRepository.save(agent);
        return modelMapper.map(savedAgent, AgentDto.class);
    }
}