package com.example.busticketbooking.busticketbookingapi.dto.request;

import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BusWIthRouteData {
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
}
