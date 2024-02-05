package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.response.SeatDto;
import com.example.busticketbooking.busticketbookingapi.entity.Seat;
import com.example.busticketbooking.busticketbookingapi.repository.SeatRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    @Override
    public List<SeatDto> getSeatsByBusId(Long busId) {
        List<Seat> seats = seatRepository.findByBusId(busId);
        return convertSeatToSeatDtos(seats); // returns a List<SeatDtos>
    }


    @Transactional
    public List<Seat> reserveSeat(List<Long> seatsId) {
        List<Seat> seats = new ArrayList<>();

        for (Long seatId : seatsId) {
            try {
                System.out.println("Trying to find seat with ID: " + seatId);
                Optional<Seat> seatOptional = seatRepository.findById(seatId);

                if (!seatOptional.isPresent())
                    throw new NoSuchElementException("Seat with id " + seatId + " not found");
                Seat seat = seatOptional.get();
                System.out.println("Seat of id " + seat.getId() + " present");

                if (seat.getIsReserved())
                    throw new RuntimeException("Seat number " + seat.getId() + " is already booked");

                seat.setIsReserved(true);
                seats.add(seat);
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("Seat id not found");
            }
        }
        return seats;
    }


//


    private List<SeatDto> convertSeatToSeatDtos(List<Seat> seats) {
        List<SeatDto> seatDtos = new ArrayList<>();

        for (Seat seat : seats) {
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
