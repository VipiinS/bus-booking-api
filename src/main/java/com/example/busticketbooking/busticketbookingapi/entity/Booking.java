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

    public Booking(User user,Bus bus,Route route,List<Seat> seats,BigDecimal farePrice){
        Booking booking = new Booking();
        booking.setSeats_Booked(seats);
        booking.setUser(user);
        booking.setBus(bus);
        booking.setRoute(route);
        booking.setDate(route.getDate());
        booking.setFare(farePrice);
    }

}
