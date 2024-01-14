package com.example.busticketbooking.busticketbookingapi.service;

import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Bus> findBusByRoute(String origin, String destination) {
        System.out.println("in impl");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bus> cq = cb.createQuery(Bus.class);
        Root<Bus> root = cq.from(Bus.class);
        cq.select(root);

        cq.where(
                cb.equal(root.get("route").get("origin"),origin),
                cb.equal(root.get("route").get("destination"),destination)
        );
        return entityManager.createQuery(cq)
                .getResultList();
    }
}