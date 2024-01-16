package com.example.busticketbooking.busticketbookingapi.dto;

import com.example.busticketbooking.busticketbookingapi.entity.Route;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class BusDto {
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private Route route;
    private BigDecimal fare;
    private Long routeId;
}
