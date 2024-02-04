package com.example.busticketbooking.busticketbookingapi.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    String Username;
    String password;
    String email;
}
