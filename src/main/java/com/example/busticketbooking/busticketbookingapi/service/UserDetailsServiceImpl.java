package com.example.busticketbooking.busticketbookingapi.service;

import com.example.busticketbooking.busticketbookingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByUsername(username);

        System.out.println("inside load by user,username: "+  userDetails.getUsername());
        if(userDetails == null){
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return  userDetails;    }
}
