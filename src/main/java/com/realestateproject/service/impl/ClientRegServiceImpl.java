package com.realestateproject.service.impl;

import com.realestateproject.entities.ClientReg;

import com.realestateproject.exception.GenericNotFoundException;
import com.realestateproject.payload.ClientRegDto;
import com.realestateproject.repositories.ClientRegRepository;
import com.realestateproject.service.ClientRegService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientRegServiceImpl implements ClientRegService {

    private ClientRegRepository clientRegRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public ClientRegServiceImpl(ClientRegRepository clientRegRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.clientRegRepository = clientRegRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ClientRegDto saveClient(ClientRegDto clientRegDto) {
        ClientReg clientReg = modelMapper.map(clientRegDto, ClientReg.class);
        clientReg.setPassword(passwordEncoder.encode(clientRegDto.getPassword()));
        ClientReg save = clientRegRepository.save(clientReg);
        return modelMapper.map(save, ClientRegDto.class);
    }

    public List<ClientRegDto> getAllClients() {
        return clientRegRepository.findAll().stream()
                .map(clientReg -> modelMapper.map(clientReg, ClientRegDto.class))
                .collect(Collectors.toList());
    }

    public Optional<ClientRegDto> getClientById(Long id) {
        return clientRegRepository.findById(id)
                .map(clientReg -> modelMapper.map(clientReg, ClientRegDto.class));
    }

    public void deleteClient(Long id) {
        clientRegRepository.findById(id).orElseThrow(
                ()->new GenericNotFoundException(id, "Client", HttpStatus.NOT_FOUND)
        );
        clientRegRepository.deleteById(id);
    }

    public ClientRegDto updateClient(Long id, ClientRegDto clientRegDto) {
        Optional<ClientReg> optionalClientReg = clientRegRepository.findById(id);
        if (optionalClientReg.isPresent()) {
            ClientReg clientReg = optionalClientReg.get();
            clientReg.setClientName(clientRegDto.getClientName());
            clientReg.setClientAddress(clientRegDto.getClientAddress());
            clientReg.setPhoneNumber(clientRegDto.getPhoneNumber());
            clientReg.setEmail(clientRegDto.getEmail());
            clientReg.setGender(clientRegDto.getGender());
            clientReg.setUsername(clientRegDto.getUsername());
            clientReg.setPassword(passwordEncoder.encode(clientRegDto.getPassword()));
            clientReg = clientRegRepository.save(clientReg);
            return modelMapper.map(clientReg, ClientRegDto.class);
        } else {
            throw new GenericNotFoundException(id, "Client", HttpStatus.NOT_FOUND);
        }
    }
}