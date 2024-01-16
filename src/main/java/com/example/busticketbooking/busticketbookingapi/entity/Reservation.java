package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "reservation")
    private List<Seat> seats;

    private LocalDateTime reservationTimestamp;
    private LocalDateTime expirationTime;

}
