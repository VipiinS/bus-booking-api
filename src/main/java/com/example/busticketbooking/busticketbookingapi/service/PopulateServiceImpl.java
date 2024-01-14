package com.example.busticketbooking.busticketbookingapi.service;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.RouteDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.repository.RouteRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PopulateServiceImpl implements PopulateService {


    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    public void populateRoutes(RouteDto routeData) {
        Route route = new Route();
        route.setOrigin(routeData.getOrigin());
        route.setDestination(routeData.getDestination());
        route.setDate(routeData.getDate());
        route.setDepartureTime(routeData.getDepartureTime());
        route.setArrivalTime(routeData.getArrivalTime());

        routeRepository.save(route);


    }

    @Override
    public void populateBus(BusDto busData) {
        Optional<Route> route = routeRepository.findById(busData.getRouteId());

        if (route.isPresent()) {

            Bus bus = new Bus();

            bus.setRegistrationNumber(busData.getRegistrationNumber());
            bus.setCapacity(busData.getCapacity());
            bus.setFare(busData.getFare());
            bus.setType(busData.getType());
            bus.setRoute(route.get());

            busRepository.save(bus);
        }
        else{
            String hi = "no";
            Object Route = new Route();
            throw new ObjectNotFoundException(Route,hi);
        }
    }



}
