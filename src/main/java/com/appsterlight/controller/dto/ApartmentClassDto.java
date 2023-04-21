package com.appsterlight.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Data
@Getter
public class ApartmentClassDto implements Serializable {
private Long id;
private String name;
private String description;

}


