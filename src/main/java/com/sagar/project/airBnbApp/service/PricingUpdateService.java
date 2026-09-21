package com.sagar.project.airBnbApp.service;

import com.sagar.project.airBnbApp.entity.Hotel;
import com.sagar.project.airBnbApp.entity.HotelMinPrice;
import com.sagar.project.airBnbApp.entity.Inventory;
import com.sagar.project.airBnbApp.repository.HotelMinPriceRepository;
import com.sagar.project.airBnbApp.repository.HotelRepository;
import com.sagar.project.airBnbApp.repository.InventoryRepository;
import com.sagar.project.airBnbApp.strategy.PricingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PricingUpdateService {
    private final InventoryRepository inventoryRepository;
    //scheduler to update inventory and HotelMinPrice tables every hours

    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final InventoryService inventoryService;
    private final HotelRepository hotelRepository;
    private final PricingService pricingService;

 //   @Scheduled(cron ="*/5 * * * * *") // every hour
 @Scheduled(cron ="0 0 * * * *") // every hour
 public void updatePrices() {
        log.info("Updating pricing...");
        int page =0;
        int batchSize =100;
        while (true) {
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page, batchSize));
            if (hotelPage.isEmpty()) {
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPrices);

            page++;
        }
    }

    private void updateHotelPrices(Hotel hotel) {
        log.info("updating hotel prices for hotel ID :{}", hotel.getId());
        LocalDate startDate=LocalDate.now();
        LocalDate endDate=startDate.plusYears(1);
        List<Inventory> inventoryList= inventoryRepository.findByHotelAndDateBetween(hotel,startDate,endDate);
        updateInvertoryPrices(inventoryList);
        updateHotelMinPrices(hotel,inventoryList,startDate,endDate);
    }

    private void updateHotelMinPrices(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {
//compute minimum price per day for thr hotel
        Map<LocalDate, BigDecimal> dailyMinPrices = inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice, Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().orElse(BigDecimal.ZERO)
                ));
        //prepare hotelprice entity in bulk
            List<HotelMinPrice> hotelPrices= new ArrayList<>();
            dailyMinPrices.forEach((date, price) -> {
                HotelMinPrice hotelPrice = hotelMinPriceRepository.findByHotelAndDate(hotel, date).orElse(new HotelMinPrice(hotel,date));
                hotelPrice.setPrice(price);
                hotelPrices.add(hotelPrice);
            });
            hotelMinPriceRepository.saveAll(hotelPrices);

    }

    private void updateInvertoryPrices(List<Inventory> inventoryList){
        inventoryList.forEach(inventory -> {
            BigDecimal dynamicPrice= pricingService.calaculateDynamicPrice(inventory);
            inventory.setPrice(dynamicPrice);

        });
        inventoryRepository.saveAll(inventoryList);
    }

}
