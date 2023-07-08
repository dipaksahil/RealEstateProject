package com.realestateproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// Sale Entity
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_Id")
    private Long saleId;

    @Column(name = "saleDate")
    private LocalDateTime saleDate;// Default value set to the current date
    // Constructor to set the saleDate field to the current date and time
    @ManyToOne
    @JoinColumn(name = "agent_agent_Id", referencedColumnName = "agentId")
    private Agent agent;

    //@ManyToOne
    //@JoinColumn(name = "property_Id", referencedColumnName = "propertyId")
    @Column(name = "property_Id")
    private Long propertyId;

    @ManyToOne
    @JoinColumn(name = "client_Id", referencedColumnName = "clientId")
    private ClientReg clientReg;

}