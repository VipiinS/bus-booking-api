package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "bus")  // Explicitly define the table name
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String registrationNumber;
    private Integer capacity;
    private String type;
    @OneToMany(mappedBy = "bus")
    private List<Seat> seats;

    @ManyToOne
    private Route route;
}