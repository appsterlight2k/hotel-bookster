package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.ApartmentClass;
import com.appsterlight.model.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DtoUtils {

    public static UserDto mapUserToDto(Optional<User> user) throws ServiceException {
        try {
            User obj = user.orElseThrow(ServiceException::new);
            return UserDto.builder()
                    .id(obj.getId())
                    .firstName(obj.getFirstName())
                    .lastName(obj.getLastName())
                    .email(obj.getEmail())
                    .role(obj.getRole())
                    .build();
        } catch (ServiceException e) {
            log.error("Exception in mapUserToDto: The user is null!");
            throw new ServiceException(e);
        }
    }

    public static ApartmentDto mapApartmentToDto(Optional<Apartment> apartment) throws ServiceException {
        try {
            Apartment obj = apartment.orElseThrow(ServiceException::new);

            return ApartmentDto.builder()
                    .id(obj.getId())
                    .apartmentNumber(obj.getApartmentNumber())
                    .roomsCount(obj.getRoomsCount())
                    .classId(obj.getClassId())
                    .adultsCapacity(obj.getAdultsCapacity())
                    .childrenCapacity(obj.getChildrenCapacity())
                    .mainPhotoUrl(obj.getMainPhotoUrl())
                    .price(obj.getPrice())
                    .className(obj.getClassName())
                    .classDescription(obj.getClassDescription())
                    .description(obj.getDescription())
                    .build();
        } catch (ServiceException e) {
            log.error("Exception in mapApartmentToDto: The apartment is null!");
            throw new ServiceException(e);
        }
    }

    public static List<ApartmentDto> mapApartmentListToDtoList(List<Apartment> list) {
        List<ApartmentDto> apartments = new ArrayList<>();
        try {
            for (Apartment ap : list) {
                apartments.add(mapApartmentToDto(Optional.of(ap)));
            }
        } catch (ServiceException e) {
            log.error("Can't map Apartment list to DTO list! " + e.getMessage());
            throw new RuntimeException(e);
        }

        return apartments;
    }

    public static ApartmentClassDto mapApartmentClassToDto(Optional<ApartmentClass> apartmentClass) throws ServiceException {
        try {
            ApartmentClass obj = apartmentClass.orElseThrow(ServiceException::new);

            return ApartmentClassDto.builder()
                    .id(obj.getId())
                    .name(obj.getName())
                    .description(obj.getDescription())
                    .build();
        } catch (ServiceException e) {
            log.error("Exception in mapApartmentToDto: The apartment is null!");
            throw new ServiceException(e);
        }
    }

    public static List<ApartmentClassDto> mapApartmentClassListToDtoList(List<ApartmentClass> list) {
        List<ApartmentClassDto> apartmentClasses = new ArrayList<>();
        try {
            for (ApartmentClass ap : list) {
                apartmentClasses.add(mapApartmentClassToDto(Optional.of(ap)));
            }
        } catch (ServiceException e) {
            log.error("Can't map Apartment Class list to DTO list! " + e.getMessage());
            throw new RuntimeException(e);
        }

        return apartmentClasses;
    }
}
