package com.realestateproject.controllers;

import com.realestateproject.payload.ClientRegDto;
import com.realestateproject.service.ClientRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientRegController {

    @Autowired
    private ClientRegService clientRegService;
    // http://localhost:8080/api/clients/createClient
    @PostMapping("/createClient")
    public ResponseEntity<ClientRegDto> createClient(@RequestBody ClientRegDto clientRegDto) {
        ClientRegDto clientDto = clientRegService.saveClient(clientRegDto);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }
    // http://localhost:8080/api/clients
    @GetMapping
    public ResponseEntity<List<ClientRegDto>> getAllClients() {
        return new ResponseEntity<>(clientRegService.getAllClients(), HttpStatus.OK);
    }
    // http://localhost:8080/api/clients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ClientRegDto> getClientById(@PathVariable Long id) {
        Optional<ClientRegDto> clientDto = clientRegService.getClientById(id);
        if (clientDto.isPresent()) {
            return new ResponseEntity<>(clientDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // http://localhost:8080/api/clients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientRegService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // // http://localhost:8080/api/clients{id}
    @PutMapping("/{id}")
    public ResponseEntity<ClientRegDto> updateClient(@PathVariable Long id, @RequestBody ClientRegDto clientRegDto) {
        ClientRegDto updatedClient = clientRegService.updateClient(id, clientRegDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}