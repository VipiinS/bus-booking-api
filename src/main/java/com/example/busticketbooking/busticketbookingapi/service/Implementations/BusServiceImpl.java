package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.response.AllBusesDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<AllBusesDto>findBusesByRoute(String pickup,String destination){
        List<Bus> buses = busRepository.findByRoute_PickupAndRoute_Destination(pickup, destination);
        List<AllBusesDto> allBusesDtos = convertBusToBusDto(buses);
        return allBusesDtos;
    }


    public List<AllBusesDto> convertBusToBusDto(List<Bus> buses) {
        List<AllBusesDto> allBusesDtos = new ArrayList<>();

        for(Bus bus : buses){

            AllBusesDto allBusesDto = new AllBusesDto();
            allBusesDto.setRegistrationNumber(bus.getRegistrationNumber());
            allBusesDto.setFare(bus.getFare());
            allBusesDto.setType(bus.getType());
            allBusesDto.setCapacity(bus.getCapacity());
            allBusesDto.setDepartureTime(bus.getDepartureTime());
            allBusesDto.setDestinationTIme(bus.getDestinationTime());

            allBusesDtos.add(allBusesDto);
        }
        return allBusesDtos;
    }
}
