package com.appsterlight.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;


@Builder
@Data
@Getter
@EqualsAndHashCode(of = {"userId", "apartmentId", "requestClassId", "checkIn", "checkOut", "adultsNumber", "reservationTime"})
public class BookingDto implements Serializable {
    private Long id;
    private Long userId;
    private Long apartmentId;
    private Integer requestClassId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer adultsNumber;
    private Integer childrenNumber;
    private String reservationTime;
    private String comments;
    private Boolean isApproved;
    private Boolean isBooked;
    private Boolean isPaid;
    private Boolean isCanceled;

    //additional fields:

    //user details:
    private String firstName;
    private String lastName;
    private String email;
    private String userPhoneNumber;
    private String userDescription;

    //apartment details:
    private String apartmentNumber;
    private String apartmentClass;
    private Integer roomsCount;
    private Integer capacity;
    private Integer price;

}


