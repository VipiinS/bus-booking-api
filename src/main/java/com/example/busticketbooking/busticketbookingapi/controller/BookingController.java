package com.example.busticketbooking.busticketbookingapi.controller;


import com.example.busticketbooking.busticketbookingapi.dto.BookedDto;
import com.example.busticketbooking.busticketbookingapi.dto.SeatBookingDto;
import com.example.busticketbooking.busticketbookingapi.entity.Booking;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BookingService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Transactional
    @PostMapping("/seats")
    public ResponseEntity<?> bookSeatById(@RequestBody SeatBookingDto request){
        try {
            BookedDto bookedDto = bookingService.bookSeatsBySeatsId(request);
            System.out.println("above response");
            return ResponseEntity.ok(bookedDto);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
