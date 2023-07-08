package com.realestateproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ClientReg")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientReg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "clientAddress")
    private String clientAddress;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "clientReg", cascade = CascadeType.ALL)
    private List<Sale> sales;
}

