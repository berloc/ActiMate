package com.codecool.fittinder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UserDto {

    @Email
    @NotNull
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
}
