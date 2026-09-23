package com.sagar.project.airBnbApp.repository;

import com.sagar.project.airBnbApp.entity.Guest;
import com.sagar.project.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}