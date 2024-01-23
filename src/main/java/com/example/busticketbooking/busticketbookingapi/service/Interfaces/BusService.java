package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.BusDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;

import java.util.List;


public interface BusService {
    public List<Bus> findBusByRoute(String origin, String destination);

    public List<BusDto> convertBusToBusDto(List<Bus> buses);
}
