package com.realestateproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agentId")
    private Long agentId;

    @Column(name = "agentName")
    private String agentName;

    @Column(name = "office")
    private String office;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    // Other fields for the agent...

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Sale> sales;
}
