package com.appsterlight.model.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestBookingExtended extends Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //additional fields:
    private String firstName;
    private String lastName;
    private String email;
    private String userPhoneNumber;
    private String userDescription;
    private String apartmentClass;

}
