package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "seat")  // Explicitly define the table name
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name="isBooked")
    private Boolean isBooked;

    @Column(length = 20)
    private String type;

    @Column(precision = 10, scale = 2)  // Adjust precision and scale if required
    private BigDecimal price;
    @ManyToOne
    private Bus bus;


}