package com.realestateproject.security;

import com.realestateproject.entities.ClientReg;
import com.realestateproject.repositories.ClientRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ClientRegRepository clientRegRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientReg clientReg = clientRegRepository.findByEmail(email);
        if (clientReg == null) {
            throw new UsernameNotFoundException("Client not found");
        }
        return new org.springframework.security.core.userdetails.User(clientReg.getEmail(), clientReg.getPassword(), new ArrayList<>());
    }
}