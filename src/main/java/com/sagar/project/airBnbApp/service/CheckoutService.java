package com.sagar.project.airBnbApp.service;

import com.sagar.project.airBnbApp.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}

