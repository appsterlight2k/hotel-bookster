package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.context.AppContext;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.User;
import com.appsterlight.service.ApartmentPhotosService;
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
                    .className("")
                    .classDescription("")
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
            throw new RuntimeException(e);
        }

        return apartments;
    }
}
