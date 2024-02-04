package com.example.busticketbooking.busticketbookingapi.dto;

import com.example.busticketbooking.busticketbookingapi.entity.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public BookedDto(Booking booking,Bus bus,Route route,List<Seat> seats,BigDecimal farePrice){
        BookedDto bookedDto = new BookedDto();
        bookedDto.setBookingId(booking.getId());
        bookedDto.setBookedDate(booking.getDate());
        bookedDto.setFare(farePrice);
        bookedDto.setBooked(booking.getIsConfirmed());
        bookedDto.setBusNumber(bus.getRegistrationNumber());
        bookedDto.setOrigin(route.getOrigin());
        bookedDto.setDestination(route.getDestination());
        bookedDto.setDate(route.getDate());
        bookedDto.setDepartureTime(route.getDepartureTime());
        bookedDto.setArrivalTime(route.getArrivalTime());

        List<Integer> seatNumbers = new ArrayList<>();
        for (Seat seat: seats) {
            seatNumbers.add(seat.getSeatNumber());
        }
        bookedDto.setSeats_Booked(seatNumbers);
    }
}


