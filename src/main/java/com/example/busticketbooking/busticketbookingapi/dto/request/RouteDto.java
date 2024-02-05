package com.example.busticketbooking.busticketbookingapi.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RouteDto {
    private String pickup;
    private String destination;
    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}
