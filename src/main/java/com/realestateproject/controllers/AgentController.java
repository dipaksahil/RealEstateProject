package com.realestateproject.controllers;

import com.realestateproject.payload.AgentDto;
import com.realestateproject.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }
// http://localhost:8080/api/agents
    @PostMapping
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentDto agentDto) {
        AgentDto createdAgent = agentService.saveAgent(agentDto);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }
    // http://localhost:8080/api/agents
    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        List<AgentDto> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }
    // http://localhost:8080/api/agents/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable Long id) {
        AgentDto agent = agentService.getAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }
    // http://localhost:8080/api/agents/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // http://localhost:8080/api/agents/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AgentDto> updateAgent(@PathVariable Long id, @RequestBody AgentDto agentDto) {
        AgentDto updatedAgent = agentService.updateAgent(id, agentDto);
        return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
    }
}