package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private BigDecimal fare;
    private Boolean isConfirmed;
    @ManyToOne
    private Bus bus;
    @ManyToOne
    private Route route;
    @OneToMany
    private List<Seat> seats_Booked;
    @ManyToOne
    private User user;

}
