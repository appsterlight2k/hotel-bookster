package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.dto.ApartmentClassDto;
import com.appsterlight.controller.dto.ApartmentDto;
import com.appsterlight.controller.dto.BookingDto;
import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
                    .isUnavailable(obj.getIsUnavailable())
                    .status(obj.getStatus())
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

    public static BookingDto mapBookingToDto(BookingExtended booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .apartmentId(booking.getApartmentId())
                .requestClassId(booking.getRequestClassId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .adultsNumber(booking.getAdultsNumber())
                .childrenNumber(booking.getChildrenNumber())
                .reservationTime(changeDateTimeFormat(booking.getReservationTime()))
                .comments(booking.getComments())
                .isOffered(booking.getIsOffered())
                .isApproved(booking.getIsApproved())
                .isBooked(booking.getIsBooked())
                .isPaid(booking.getIsPaid())
                .isCanceled(booking.getIsCanceled())
                //additional fields:
                .firstName(booking.getFirstName())
                .lastName(booking.getLastName())
                .email(booking.getEmail())
                .userPhoneNumber(booking.getUserPhoneNumber())
                .userDescription(booking.getUserDescription())
                .apartmentNumber(booking.getApartmentNumber())
                .apartmentClass(booking.getApartmentClass())
                .roomsCount(booking.getRoomsCount())
                .capacity(booking.getCapacity())
                .price(booking.getPrice())
                .build();
    }

    public static BookingDto mapRequestBookingToDto(RequestBookingExtended booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .apartmentId(booking.getApartmentId())
                .requestClassId(booking.getRequestClassId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .adultsNumber(booking.getAdultsNumber())
                .childrenNumber(booking.getChildrenNumber())
                .reservationTime(changeDateTimeFormat(booking.getReservationTime()))
                .comments(booking.getComments())
                .isOffered(booking.getIsOffered())
                .isApproved(booking.getIsApproved())
                .isBooked(booking.getIsBooked())
                .isPaid(booking.getIsPaid())
                .isCanceled(booking.getIsCanceled())
                //additional fields:
                .firstName(booking.getFirstName())
                .lastName(booking.getLastName())
                .email(booking.getEmail())
                .userPhoneNumber(booking.getUserPhoneNumber())
                .userDescription(booking.getUserDescription())
                .apartmentClass(booking.getApartmentClass())
                .build();
    }

    public static List<BookingDto> mapBookingListToDtoList(List<Booking> list) {
        List<BookingDto> bookingDtoList = new ArrayList<>();
        if (!list.isEmpty()) {
            if (list.get(0) instanceof BookingExtended) {
                for (Booking ap : list) {
                    bookingDtoList.add(mapBookingToDto((BookingExtended) ap));
                }
            } else {
                //RequestBookingExtended
                for (Booking ap : list) {
                    bookingDtoList.add(mapRequestBookingToDto((RequestBookingExtended) ap));
                }
            }
        }

        return bookingDtoList;
    }

    private static String changeDateTimeFormat(Timestamp inputDateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return dateFormat.format(inputDateTime);
    }
}
