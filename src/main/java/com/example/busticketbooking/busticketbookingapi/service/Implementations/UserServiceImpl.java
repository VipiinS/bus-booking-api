package com.example.busticketbooking.busticketbookingapi.service.Implementations;

import com.example.busticketbooking.busticketbookingapi.dto.JwtResponseDTO;
import com.example.busticketbooking.busticketbookingapi.dto.SignInDto;
import com.example.busticketbooking.busticketbookingapi.dto.UserRegisterDto;
import com.example.busticketbooking.busticketbookingapi.entity.Role;
import com.example.busticketbooking.busticketbookingapi.entity.User;
import com.example.busticketbooking.busticketbookingapi.repository.UserRepository;
import com.example.busticketbooking.busticketbookingapi.service.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(UserRegisterDto requestDto){

        // Check if the user already exists
        if (userRepository.existsByUsername(requestDto.getUsername()) || userRepository.existsByEmail(requestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User already exists");
        }

        User user = new User();

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstname());
        user.setLastName(requestDto.getLastname());


        // Convert role names to Role enum instances
        List<Role> userRoles = new ArrayList<>();

        for (String roleString : requestDto.getRoles()) {
            Role role = Role.valueOf(roleString);
            userRoles.add(role);
        }

        user.setRoles(userRoles);

        try {
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to save in db");
        }

    }

    @Override
    public ResponseEntity<JwtResponseDTO> signInUser(SignInDto requestDTO) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));

        if(authentication.isAuthenticated()){
            // Extract roles from the Authentication object
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


            // Convert GrantedAuthority objects to role strings
            List<String> roles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());


            JwtResponseDTO responseDTO = new JwtResponseDTO();
            responseDTO.setToken(jwtService.generateToken(requestDTO.getUsername(), roles));
            return ResponseEntity.ok(responseDTO);

        }else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

}
