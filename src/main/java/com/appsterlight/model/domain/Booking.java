package com.appsterlight.model.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    private Integer requestClassId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer adultsNumber;
    private Integer childrenNumber;
    private Timestamp reservationTime;
    private String comments;
    private Boolean isApproved;
    private Boolean isBooked;
    private Boolean isPaid;
    private Boolean isCanceled;

}
