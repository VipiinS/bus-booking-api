package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.SeatDto;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeatService {
     List<SeatDto> getSeatsByBusId(Long busId);
     public List<Seat> reserveSeat(List<Long> seats);

}
