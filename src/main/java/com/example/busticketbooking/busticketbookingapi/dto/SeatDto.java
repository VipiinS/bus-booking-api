package com.example.busticketbooking.busticketbookingapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatDto {
    private Integer seatNumber;
    private Boolean isBooked;
    private String type;
    private BigDecimal price;
}
