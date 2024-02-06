package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.response.AllBusesDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;

import java.util.List;


public interface BusService {

    public List<AllBusesDto> convertBusToBusDto(List<Bus> buses);
    public List<AllBusesDto>findBusesByRoute(String pickup,String destination);

    }
