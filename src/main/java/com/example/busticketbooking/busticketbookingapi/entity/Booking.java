package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Long id;
    private LocalDate date;
    private List<Seat> seats;
    private BigDecimal fare;
    private String status;
    private String ticketId;

    @ManyToOne
    private User user;
    @ManyToOne
    private Bus bus;
    @ManyToOne
    private Route route;
}
