package com.example.busticketbooking.busticketbookingapi.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AllRoutesDto {
    List<String> origin;
    List<String> destination;

    public AllRoutesDto(List<String> origins, List<String> destinations){
        this.origin = origins;
        this.destination = destinations;
    }
}
