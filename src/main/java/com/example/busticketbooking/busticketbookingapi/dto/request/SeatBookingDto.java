package com.example.busticketbooking.busticketbookingapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBookingDto {
    Long busId;
    String username;
    List<Long> seatIds;
}
