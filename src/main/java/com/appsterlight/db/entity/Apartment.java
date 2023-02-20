package com.appsterlight.db.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Apartment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String apartmentNumber;
    private Integer roomsCount;
    private Integer classId;
    private Integer adultsCapacity;
    private Integer childrenCapacity;
    private Integer price;
    private String description;

//    ApartmentClass apartmentClass;
//    ApartmentPhotos apartmentPhotos;

}
