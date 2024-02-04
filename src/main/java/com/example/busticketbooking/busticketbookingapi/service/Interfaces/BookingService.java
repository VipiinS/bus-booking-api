package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.response.BookedDto;
import com.example.busticketbooking.busticketbookingapi.dto.SeatBookingDto;


public interface BookingService {
     public BookedDto bookSeatsBySeatsId(SeatBookingDto seatBookingDto);
}
