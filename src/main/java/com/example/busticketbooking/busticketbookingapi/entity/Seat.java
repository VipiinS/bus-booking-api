package com.example.busticketbooking.busticketbookingapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    private Long id;
    private Integer seatNumber;
    private String status;
    private String type;
    private BigDecimal price;

    @ManyToOne
    private Bus bus;
}
