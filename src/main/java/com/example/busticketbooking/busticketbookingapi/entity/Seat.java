package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name="isBooked")
    private Boolean isBooked;

    @Column(name = "isReserved")
    private Boolean isReserved;

    @Column(length = 20)
    private String type;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    private Bus bus;


    public void Seat(){
        this.isReserved = false;
        this.isBooked = false;
    }
}