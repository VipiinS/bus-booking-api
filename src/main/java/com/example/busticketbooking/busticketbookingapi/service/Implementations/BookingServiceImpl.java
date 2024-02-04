package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.response.BookedDto;
import com.example.busticketbooking.busticketbookingapi.dto.SeatBookingDto;
import com.example.busticketbooking.busticketbookingapi.entity.*;
import com.example.busticketbooking.busticketbookingapi.repository.BookingRepository;
import com.example.busticketbooking.busticketbookingapi.repository.BusRepository;
import com.example.busticketbooking.busticketbookingapi.repository.RouteRepository;
import com.example.busticketbooking.busticketbookingapi.repository.UserRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BookingService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SeatService seatService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookedDto bookSeatsBySeatsId(SeatBookingDto bookingDto) {
        List<Seat> seats = seatService.reserveSeat(bookingDto.getSeatIds());

        //fetching user
        User user = userRepository.findByUsername(bookingDto.getUsername());
        if(user == null) throw new UsernameNotFoundException("user: " + bookingDto.getUsername() +" not found");

        //fetching Bus
        Optional<Bus> busOptional= busRepository.findById(bookingDto.getBusId());
        if(!busOptional.isPresent()) throw new NoSuchElementException("bus of id:"+ bookingDto.getBusId() +" not found");
        Bus bus = busOptional.get();

        //fetching route
        Optional<Route> routeOptional = routeRepository.findById(bus.getRoute().getId());
        if(!routeOptional.isPresent()) throw new NoSuchElementException("route  not found");
        Route route = routeOptional.get();


        //calculating fare price
        BigDecimal seatQuantity = BigDecimal.valueOf(seats.size());
        BigDecimal farePrice = bus.getFare().multiply(seatQuantity);

        //creating a Booking object to save in the DB
        Booking booking = new Booking(user,bus,route,seats,farePrice);
        booking.setIsConfirmed(true);
        bookingRepository.save(booking);

        // returning a Response of Booked Dto
        return new BookedDto(booking,bus,route,seats,farePrice);

    }
}
