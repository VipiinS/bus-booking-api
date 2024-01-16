package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.SeatDto;

import java.util.List;

public interface SeatService {
     List<SeatDto> getSeatsByBusId(Long busId);
}
