package com.codecool.fittinder.model.dto;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class ProfileDto {

    private User user;
    private List<Interest> interestList;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String location;
}
