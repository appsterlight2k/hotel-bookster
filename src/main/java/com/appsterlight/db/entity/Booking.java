package com.appsterlight.db.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Long apartmentId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer adultsNumber;
    private Integer childrenNumber;
    private Timestamp reservationTime;
    private Boolean isApproved;
    private Boolean isBooked;
    private Boolean isPaid;
    private Boolean isCanceled;

//    private User user;
//    private Apartment apartment;
}
