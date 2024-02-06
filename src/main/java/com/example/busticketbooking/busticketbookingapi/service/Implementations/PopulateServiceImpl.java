package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.request.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.request.RouteDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.repository.RouteRepository;
import com.example.busticketbooking.busticketbookingapi.repository.SeatRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PopulateServiceImpl implements PopulateService {


    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private SeatRepository seatRepository;

    public Long populateRoutes(RouteDto routeData) {
        if(routeData.getPickup() == null || routeData.getDestination() == null){
            throw new RuntimeException("Route needs origin and destination to persist");
        }
        Route route = new Route(routeData);
        Route savedRoute = routeRepository.save(route);

        return savedRoute.getId();
    }

    @Override
    public Bus populateBus(BusDto busData) {

        Optional<Route> routeOptional = routeRepository.findByPickupAndDestination(busData.getPickup(), busData.getDestination());
        Route route;
        if (routeOptional.isPresent()) {
            route = routeOptional.get();
        } else {
            // Create a new route if it doesn't exist
            route = new Route();
            route.setPickup(busData.getPickup());
            route.setDestination(busData.getDestination());
            // Set other route properties as needed
            route = routeRepository.save(route);

        }

        // Creating the bus
        Bus bus = new Bus(busData,route);
        busRepository.save(bus);

        // Populate seats for the bus
        populateSeats(bus);
        return bus;
    }

    @Override
    public void populateSeats(Bus bus) {
        for (int i = 1; i <= bus.getCapacity(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setIsBooked(false);  // Initially set all seats as available
            seat.setType("Regular");
            seat.setPrice(bus.getFare());  // Inherit fare from bus
            seat.setBus(bus);
            seat.setIsReserved(false);
            seatRepository.save(seat);  // Save
        }
    }


    public class RouteNotFoundException extends RuntimeException {
        public RouteNotFoundException(long routeId) {
            super("Route with ID " + routeId + " not found");
        }
    }
}
