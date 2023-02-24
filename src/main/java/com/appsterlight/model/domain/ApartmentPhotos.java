package com.appsterlight.model.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApartmentPhotos implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String path;
    private Long apartmentId;
//    private Apartment apartment;

}
