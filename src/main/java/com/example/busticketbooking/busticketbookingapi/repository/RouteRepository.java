package com.example.busticketbooking.busticketbookingapi.repository;

import com.example.busticketbooking.busticketbookingapi.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

 Optional<Route> findById(Long routeId);
 @Query("SELECT DISTINCT r.origin FROM Route r")
 List<String> findAllOrigins();
 @Query("SELECT DISTINCT r.destination FROM Route r")
 List<String> findAllDestinations();
}
