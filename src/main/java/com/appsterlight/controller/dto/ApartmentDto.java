package com.appsterlight.controller.dto;

import lombok.*;

import java.io.Serializable;


@Builder
@Data
@Getter
public class ApartmentDto implements Serializable {

    private Long id;
    private String apartmentNumber;
    private Integer roomsCount;
    private Integer classId;
    private Integer adultsCapacity;
    private Integer childrenCapacity;
    private String mainPhotoUrl;
    private Integer price;
    private String className;
    private String classDescription;
    private String description;
    private Boolean isUnavailable;
    private String status;

}


