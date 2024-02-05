package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.response.AllBusesDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Bus> findBusByRoute(String pickup, String destination) {

        System.out.println("in impl");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bus> cq = cb.createQuery(Bus.class);
        Root<Bus> root = cq.from(Bus.class);
        cq.select(root);

        cq.where(
                cb.equal(root.get("route").get("pickup"),pickup),
                cb.equal(root.get("route").get("destination"),destination)
        );
        try{
            List<Bus> buses = entityManager.createQuery(cq).getResultList();
            return entityManager.createQuery(cq)
                .getResultList();
        }catch (Exception e){
            throw new RuntimeException("no Buses found on requested routes..");
        }

    }
    public List<AllBusesDto> convertBusToBusDto(List<Bus> buses) {
        List<AllBusesDto> allBusesDtos = new ArrayList<>();

        for(Bus bus : buses){

            AllBusesDto allBusesDto = new AllBusesDto();
            allBusesDto.setRegistrationNumber(bus.getRegistrationNumber());
            allBusesDto.setFare(bus.getFare());
            allBusesDto.setType(bus.getType());
            allBusesDto.setCapacity(bus.getCapacity());

            allBusesDtos.add(allBusesDto);
        }
        return allBusesDtos;
    }
}
