package com.example.busticketbooking.busticketbookingapi.dto;

import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import com.example.busticketbooking.busticketbookingapi.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedDto {
    private Long bookingId;
    private LocalDate bookedDate;
    private BigDecimal fare;
    private Boolean booked;
    private String busNumber;
    private String origin;
    private String destination;
    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private List<Integer> seats_Booked;
}

