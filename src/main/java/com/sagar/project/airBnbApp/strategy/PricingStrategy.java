package com.sagar.project.airBnbApp.strategy;

import com.sagar.project.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
