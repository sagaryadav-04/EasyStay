package com.sagar.project.airBnbApp.strategy;

import com.sagar.project.airBnbApp.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
    public BigDecimal calaculateDynamicPrice(Inventory inventory){
        PricingStrategy pricingStrategy=new BasePricinngStrategy();

        //apply the additional strategies

        pricingStrategy=new SurgePricingStrategy(pricingStrategy);
        pricingStrategy=new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy=new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy=new HolidayPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }
}
