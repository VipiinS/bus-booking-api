package com.example.busticketbooking.busticketbookingapi.service;

import com.example.busticketbooking.busticketbookingapi.dao.RouteDto;
import com.example.busticketbooking.busticketbookingapi.entity.Route;
import com.example.busticketbooking.busticketbookingapi.repository.RouteRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.PopulateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PopulateServiceImpl implements PopulateService {


    @Autowired
    private RouteRepository routeRepository;
    public void populateRoutes(RouteDto routeData) {
        Route route = new Route();
        route.setOrigin(routeData.getOrigin());
        route.setDestination(routeData.getDestination());
        route.setDate(routeData.getDate());
        route.setDepartureTime(routeData.getDepartureTime());
        route.setArrivalTime(routeData.getArrivalTime());

        routeRepository.save(route);


    }

}
