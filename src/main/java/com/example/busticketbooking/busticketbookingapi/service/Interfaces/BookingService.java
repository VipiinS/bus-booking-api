package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.BookedDto;
import com.example.busticketbooking.busticketbookingapi.dto.SeatBookingDto;
import com.example.busticketbooking.busticketbookingapi.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {
     public BookedDto bookSeatsBySeatsId(SeatBookingDto seatBookingDto);
}
