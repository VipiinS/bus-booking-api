package com.example.busticketbooking.busticketbookingapi.repository;


import com.example.busticketbooking.busticketbookingapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findById(Long id);
}
