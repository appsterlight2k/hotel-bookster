package com.appsterlight.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class OfferedAparment {
    private Long id;
    private Long bookingId;
    private Long apartmentId;
    private String message;

}
