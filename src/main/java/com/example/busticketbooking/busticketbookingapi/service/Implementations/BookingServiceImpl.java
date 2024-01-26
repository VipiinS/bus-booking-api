package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.BookedDto;
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
import java.util.ArrayList;
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
        System.out.println("User: "+ user.getUsername());

        //fetching Bus
        Optional<Bus> busOptional= busRepository.findById(bookingDto.getBusId());
        if(!busOptional.isPresent()) throw new NoSuchElementException("bus of id:"+ bookingDto.getBusId() +" not found");
        Bus bus = busOptional.get();
        System.out.println("bus id: "+ bus.getId());

        //fetching route
        System.out.println("searching route " + bus.getRoute().getId());
        Optional<Route> routeOptional = routeRepository.findById(bus.getRoute().getId());
        if(!routeOptional.isPresent()) throw new NoSuchElementException("route  not found");
        Route route = routeOptional.get();
        System.out.println("route id: " + route.getId()+" found");


        //calculating fare price
        BigDecimal seatQuantity = BigDecimal.valueOf(seats.size());
        BigDecimal farePrice = bus.getFare().multiply(seatQuantity);

        Booking booking = new Booking();
        booking.setSeats_Booked(seats);
        booking.setUser(user);
        booking.setBus(bus);
        booking.setRoute(route);
        booking.setDate(route.getDate());
        booking.setFare(farePrice);
        booking.setIsConfirmed(true);
        bookingRepository.save(booking);

        BookedDto bookedDto = new BookedDto();
        bookedDto.setBookingId(booking.getId());
        bookedDto.setBookedDate(booking.getDate());
        bookedDto.setFare(farePrice);
        bookedDto.setBooked(booking.getIsConfirmed());
        bookedDto.setBusNumber(bus.getRegistrationNumber());
        bookedDto.setOrigin(route.getOrigin());
        bookedDto.setDestination(route.getDestination());
        bookedDto.setDate(route.getDate());
        bookedDto.setDepartureTime(route.getDepartureTime());
        bookedDto.setArrivalTime(route.getArrivalTime());

        List<Integer> seatNumbers = new ArrayList<>();
        for (Seat seat: seats) {
            seatNumbers.add(seat.getSeatNumber());
        }
        bookedDto.setSeats_Booked(seatNumbers);


        return bookedDto;
    }
}
