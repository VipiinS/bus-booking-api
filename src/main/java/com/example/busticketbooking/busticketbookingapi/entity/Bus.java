package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus {

    @Id
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
