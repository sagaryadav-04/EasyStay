package com.sagar.project.airBnbApp.dto;

import com.sagar.project.airBnbApp.entity.User;
import com.sagar.project.airBnbApp.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
