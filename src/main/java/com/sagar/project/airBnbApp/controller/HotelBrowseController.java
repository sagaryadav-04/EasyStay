package com.sagar.project.airBnbApp.controller;

import com.sagar.project.airBnbApp.dto.HotelDto;
import com.sagar.project.airBnbApp.dto.HotelInfoDto;
import com.sagar.project.airBnbApp.dto.HotelPriceDto;
import com.sagar.project.airBnbApp.dto.HotelSearchRequest;
import com.sagar.project.airBnbApp.service.HotelService;
import com.sagar.project.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelPriceDto> page= inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }
    @GetMapping("{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        // Implementation for fetching hotel info
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
