package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    @Id
    private  Long id;
    private String origin;
    private String destination;
    private Integer distance;
    private  Integer duration;

    @OneToMany(mappedBy = "route")
    private List<Bus> buses; // one route has many buses

}
