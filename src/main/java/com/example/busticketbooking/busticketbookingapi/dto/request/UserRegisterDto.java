package com.example.busticketbooking.busticketbookingapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<String> roles;


    //if no roles are given in a request, the default will be USER
    void UserRegisterDto(){
        this.roles.add("USER");
    }
}
