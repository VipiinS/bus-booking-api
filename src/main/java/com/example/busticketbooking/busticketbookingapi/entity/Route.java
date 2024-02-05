package com.example.busticketbooking.busticketbookingapi.entity;

import com.example.busticketbooking.busticketbookingapi.dto.request.RouteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String pickup;
    @NotNull
    private String destination;
    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @OneToMany(mappedBy = "route")
    private List<Bus> buses;

    public Route(RouteDto routeDto){
        this.pickup = routeDto.getPickup();
        this.destination = routeDto.getDestination();
        this.arrivalTime = routeDto.getArrivalTime();
        this.departureTime = routeDto.getDepartureTime();
        this.date = routeDto.getDate();
    }
}
