package com.realestateproject.service.impl;

import com.realestateproject.entities.Agent;
import com.realestateproject.entities.ClientReg;
import com.realestateproject.entities.Sale;

import com.realestateproject.exception.AgentNotFoundException;
import com.realestateproject.exception.GenericNotFoundException;
import com.realestateproject.exception.SaleNotFoundException;
import com.realestateproject.payload.SaleDto;
import com.realestateproject.repositories.AgentRepository;
import com.realestateproject.repositories.ClientRegRepository;
import com.realestateproject.repositories.SaleRepository;
import com.realestateproject.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    // Autowire repositories and modelMapper
    private SaleRepository saleRepository;
    private ClientRegRepository clientRegRepository;
    private AgentRepository agentRepository;
    private ModelMapper modelMapper;
    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ClientRegRepository clientRegRepository, AgentRepository agentRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.clientRegRepository = clientRegRepository;
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public SaleDto createSale(SaleDto saleDto) {
         // Fetch related entities using their ids and handle possible errors
//        ClientReg clientReg = clientRegRepository.findById(saleDto.getClientReg().getClientId())
//                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + saleDto.getClientReg().getClientId()));
//        Property property = propertyRepository.findById(saleDto.getPropertyId())
//                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + saleDto.getPropertyId()));
//        Agent agent = agentRepository.findById(saleDto.getAgent().getAgentId())
//                .orElseThrow(() -> new AgentNotFoundException("Agent not found with id: " + saleDto.getAgent().getAgentId()));

        // Fetch related entities using their ids and handle possible errors
        Optional<ClientReg> clientRegOpt = clientRegRepository.findById(saleDto.getClientReg().getClientId());
        if (!clientRegOpt.isPresent()) {
            throw new GenericNotFoundException(saleDto.getClientReg().getClientId(),"Client", HttpStatus.NOT_FOUND);
        }
        ClientReg clientReg = clientRegOpt.get();

        Optional<Agent> agentOpt = agentRepository.findById(saleDto.getAgent().getAgentId()); // assuming you have an agent object inside saleDto
        if (!agentOpt.isPresent()) {
            throw new AgentNotFoundException(+saleDto.getAgent().getAgentId());
        }
        Agent agent = agentOpt.get();

        Sale sale = modelMapper.map(saleDto, Sale.class);
        sale.setClientReg(clientReg);
        sale.setAgent(agent);
        Sale savedSale = saleRepository.save(sale);

        SaleDto saleResponse = modelMapper.map(savedSale, SaleDto.class);
        return saleResponse;
    }

    @Override
    public List<SaleDto> getAllSales() {
        List<Sale> allSale = saleRepository.findAll();
        return allSale.stream()
                .map(sale -> modelMapper.map(sale, SaleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SaleDto getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException(id));
        return modelMapper.map(sale, SaleDto.class);
    }

    @Override
    public SaleDto updateSale(Long id, SaleDto saleDto) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException(id));
        // You can also update the related entities as done in createSale
        //sale.setSaleDate(saleDto.getSaleDate());
        Sale updatedSale = saleRepository.save(sale);
        return modelMapper.map(updatedSale, SaleDto.class);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException(id));
        saleRepository.delete(sale);
    }
}
