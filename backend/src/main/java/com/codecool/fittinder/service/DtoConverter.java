package com.codecool.fittinder.service;

import com.codecool.fittinder.dto.UserDto;
import com.codecool.fittinder.model.User;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter {

    public User convertToUser(UserDto dto) {
        return new User(dto.getEmail(), dto.getPassword());
    }
}
