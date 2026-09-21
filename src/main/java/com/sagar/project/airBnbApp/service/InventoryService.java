package com.sagar.project.airBnbApp.service;

import com.sagar.project.airBnbApp.dto.HotelDto;
import com.sagar.project.airBnbApp.dto.HotelPriceDto;
import com.sagar.project.airBnbApp.dto.HotelSearchRequest;
import com.sagar.project.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInvertory(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
