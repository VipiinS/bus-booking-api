package com.example.busticketbooking.busticketbookingapi.repository;

import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long> {
    Optional<Bus> findById(Long busId);
}
