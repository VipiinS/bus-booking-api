package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BusService {
    public List<Bus> findBusByRoute(String origin, String destination);
}
