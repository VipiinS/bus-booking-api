package com.example.busticketbooking.busticketbookingapi.service.Interfaces;

import com.example.busticketbooking.busticketbookingapi.dto.response.JwtResponseDTO;
import com.example.busticketbooking.busticketbookingapi.dto.request.SignInDto;
import com.example.busticketbooking.busticketbookingapi.dto.request.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {
    public ResponseEntity<String> registerUser(UserRegisterDto requestDto);
    public ResponseEntity<JwtResponseDTO>  signInUser(@RequestBody SignInDto requestDTO);
}
