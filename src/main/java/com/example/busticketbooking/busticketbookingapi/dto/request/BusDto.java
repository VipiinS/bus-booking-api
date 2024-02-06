package com.example.busticketbooking.busticketbookingapi.dto.request;

import com.example.busticketbooking.busticketbookingapi.entity.Route;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class BusDto {
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private BigDecimal fare;
    private String pickup;
    private String destination;
    private LocalDate departureTime;
    private LocalDate destinationTime;


}
