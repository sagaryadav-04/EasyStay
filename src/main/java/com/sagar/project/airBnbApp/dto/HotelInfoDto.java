package com.sagar.project.airBnbApp.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotel;
    private List<RoomDto> rooms;
}
