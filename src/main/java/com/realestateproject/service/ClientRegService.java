package com.realestateproject.service;

import com.realestateproject.payload.ClientRegDto;
import java.util.List;
import java.util.Optional;

public interface ClientRegService {
    ClientRegDto saveClient(ClientRegDto clientDto);

    List<ClientRegDto> getAllClients();
    Optional<ClientRegDto> getClientById(Long id);
    public ClientRegDto updateClient(Long id, ClientRegDto clientRegDto);
    void deleteClient(Long id);
}