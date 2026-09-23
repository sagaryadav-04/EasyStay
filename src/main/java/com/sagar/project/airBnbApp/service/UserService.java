package com.sagar.project.airBnbApp.service;

import com.sagar.project.airBnbApp.dto.ProfileUpdateRequestDto;
import com.sagar.project.airBnbApp.dto.UserDto;
import com.sagar.project.airBnbApp.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
