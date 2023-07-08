package com.realestateproject.repositories;

import com.realestateproject.entities.ClientReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRegRepository extends JpaRepository<ClientReg, Long> {
    ClientReg findByEmail(String email);
}
