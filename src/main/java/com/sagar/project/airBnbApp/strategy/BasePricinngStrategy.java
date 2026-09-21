package com.sagar.project.airBnbApp.strategy;

import com.sagar.project.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public class BasePricinngStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
