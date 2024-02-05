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
        Route route = new Route();
        route.setPickup(routeData.getPickup());
        route.setDestination(routeData.getDestination());
        route.setDate(routeData.getDate());
        route.setDepartureTime(routeData.getDepartureTime());
        route.setArrivalTime(routeData.getArrivalTime());

        Route savedRoute = routeRepository.save(route);

        return savedRoute.getId();
    }

    @Override
    public void populateBus(BusDto busData) {
        Optional<Route> route;
        try {
            route = routeRepository.findById(busData.getRouteId());
        }catch (Exception e){
            throw new RouteNotFoundException(busData.getRouteId());
        }
        if (route.isPresent()) {

            Bus bus = new Bus();

            bus.setRegistrationNumber(busData.getRegistrationNumber());
            bus.setCapacity(busData.getCapacity());
            bus.setFare(busData.getFare());
            bus.setType(busData.getType());
            bus.setRoute(route.get());

            busRepository.save(bus); //*Saving Bus to get its ID for Seat generation.

            populateSeats(bus);  //*Populating Seats for the bus

        }
        else{
            throw new RuntimeException();
        }
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
