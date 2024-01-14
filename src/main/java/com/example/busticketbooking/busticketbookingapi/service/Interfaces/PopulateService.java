package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.dto.RouteDto;

public interface PopulateService {
    void populateRoutes(RouteDto routeData);

    void populateBus(BusDto busData);
}
