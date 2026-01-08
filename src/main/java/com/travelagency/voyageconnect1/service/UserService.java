package com.travelagency.voyageconnect1.service;

import com.travelagency.voyageconnect1.domain.dto.UserDto;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);
    Optional<UserDto> getUserById(Long id);
    Optional<UserDto> getUserByUsername(String username);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
