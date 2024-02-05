package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.response.AllRoutesDto;
import com.example.busticketbooking.busticketbookingapi.repository.RouteRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
private final RouteRepository routeRepository;
    @Override
    public ResponseEntity<?> getAllRoutes() {
        try {
            List<String> origin = routeRepository.findAllPickup();
            List<String> destination = routeRepository.findAllDestinations();

            AllRoutesDto allRoutesDto = new AllRoutesDto(origin,destination);
            return ResponseEntity.ok(allRoutesDto);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
