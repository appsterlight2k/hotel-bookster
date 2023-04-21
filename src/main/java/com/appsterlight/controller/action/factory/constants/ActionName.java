package com.appsterlight.controller.action.factory.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActionName {



    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_LOGOUT = "logout";
    public static final String ACTION_REGISTRATION = "registration";
    public static final String ACTION_HOME = "home";

    public static final String ACTION_MANAGER_HOME = "manager-home";


    public static final String ACTION_MANAGER_ALL_APARTMENTS = "manager-get-apartments";
    public static final String ACTION_MANAGER_ALL_REQUESTS = "manager-get-all-requests";
    public static final String ACTION_MANAGER_REQUESTS_FOR_BOOKING = "manager-get-requests-for-booking";
    public static final String ACTION_MANAGER_BOOKING_REQUESTS = "manager-get-booking-requests";
    public static final String ACTION_MANAGER_OFFER_APARTMENTS = "manager-offer-for-request";
    public static final String ACTION_MANAGER_BOOKED = "manager-get-booked";


    public static final String ACTION_APARTMENTS = "apartments";
    public static final String ACTION_GET_APARTMENT = "get-apartment";
    public static final String ACTION_BOOKING = "booking";
    public static final String ACTION_CABINET = "cabinet";
    public static final String ACTION_ERROR = "error";







}
