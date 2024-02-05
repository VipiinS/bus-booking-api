package com.example.busticketbooking.busticketbookingapi.entity;

import com.example.busticketbooking.busticketbookingapi.dto.request.BusDto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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
    @OneToMany(mappedBy = "bus")
    private List<Seat> seats;
    private String type;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    private BigDecimal fare;
    private String pickup; // Pickup location (if creating a new route)
    private String destination; // Destination location (if creating a new route)

    public Bus (BusDto busDto,Route route){
        this.setRegistrationNumber(busDto.getRegistrationNumber());
        this.setCapacity(busDto.getCapacity());
        this.setFare(busDto.getFare());
        this.setType(busDto.getType());
        this.setRoute(route);
        this.pickup = busDto.getPickup();
        this.destination = busDto.getDestination();

    }
}