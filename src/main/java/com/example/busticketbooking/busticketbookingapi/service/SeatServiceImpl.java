package com.example.busticketbooking.busticketbookingapi.service;

import com.example.busticketbooking.busticketbookingapi.dto.SeatDto;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import com.example.busticketbooking.busticketbookingapi.repository.SeatRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Override
    public List<SeatDto> getSeatsByBusId(Long busId) {
        List<Seat> seats = seatRepository.findByBusId(busId);
        return convertSeatToSeatDtos(seats); // returns a List<SeatDtos>
    }

    private List<SeatDto> convertSeatToSeatDtos(List<Seat> seats) {
        List<SeatDto> seatDtos = new ArrayList<>();

        for(Seat seat : seats){
            SeatDto seatDto = new SeatDto();

            seatDto.setSeatNumber(seat.getSeatNumber());
            seatDto.setType(seat.getType());
            seatDto.setIsBooked(seat.getIsBooked());
            seatDto.setPrice(seat.getPrice());

            seatDtos.add(seatDto);
        }
        return seatDtos;
    }
}
