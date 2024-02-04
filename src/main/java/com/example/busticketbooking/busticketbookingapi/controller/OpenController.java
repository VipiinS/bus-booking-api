package com.example.busticketbooking.busticketbookingapi.controller;

import com.example.busticketbooking.busticketbookingapi.dto.*;
import com.example.busticketbooking.busticketbookingapi.dto.request.SignInDto;
import com.example.busticketbooking.busticketbookingapi.dto.request.UserRegisterDto;
import com.example.busticketbooking.busticketbookingapi.dto.response.JwtResponseDTO;
import com.example.busticketbooking.busticketbookingapi.dto.response.SeatDto;
import com.example.busticketbooking.busticketbookingapi.entity.Bus;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.BusService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.RouteService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.SeatService;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@CrossOrigin
public class OpenController {

    @Autowired
    private BusService busService;
    @Autowired
    private SeatService seatService;
    private final UserService userService;
    private final RouteService routeService;

   @PostMapping("/")
   public String open(){
       return "Open";
   }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto){
        return userService.registerUser(userRegisterDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDTO> signIn(@RequestBody SignInDto signInDto){
        return userService.signInUser(signInDto);
    }


    @GetMapping("/searchByRoute")
    public ResponseEntity<?> findBusByRoute(@RequestParam("origin") String origin,
                                            @RequestParam("destination") String destination){
        try{
            List<Bus> buses = busService.findBusByRoute(origin,destination);
            List<BusDto> busDtos = busService.convertBusToBusDto(buses);
            return ResponseEntity.ok(busDtos);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all-routes")
    public ResponseEntity<?> getAllRoutes(){
       return routeService.getAllRoutes();
    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<?> getSeatsByBusId(@PathVariable Long busId){
        try {
            List<SeatDto> seatDtos = seatService.getSeatsByBusId(busId);
            return ResponseEntity.ok(seatDtos);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
