package com.example.busticketbooking.busticketbookingapi.repository;

import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByBusId(Long busId);
}
