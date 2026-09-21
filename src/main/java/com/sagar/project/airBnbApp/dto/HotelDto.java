package com.sagar.project.airBnbApp.dto;

import com.sagar.project.airBnbApp.entity.HotelContactInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
