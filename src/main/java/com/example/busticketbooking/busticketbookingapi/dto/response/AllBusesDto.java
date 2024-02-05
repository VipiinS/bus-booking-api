package com.example.busticketbooking.busticketbookingapi.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllBusesDto {
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private BigDecimal fare;
}
