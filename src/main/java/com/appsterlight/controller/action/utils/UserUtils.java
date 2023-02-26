package com.appsterlight.controller.action.utils;

import com.appsterlight.controller.dto.UserDto;
import com.appsterlight.exception.ServiceException;
import com.appsterlight.model.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserUtils {

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
}
