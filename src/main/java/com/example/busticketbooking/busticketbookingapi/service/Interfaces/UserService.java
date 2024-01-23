package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.JwtResponseDTO;
import com.example.busticketbooking.busticketbookingapi.dto.SignInDto;
import com.example.busticketbooking.busticketbookingapi.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {
    public ResponseEntity<String> registerUser(UserRegisterDto requestDto);
    public ResponseEntity<JwtResponseDTO>  signInUser(@RequestBody SignInDto requestDTO);
}
