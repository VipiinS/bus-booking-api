package com.example.busticketbooking.busticketbookingapi.dto;

import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class BusDto {
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private Route route;
    private double fare;
    private Long routeId;
}
