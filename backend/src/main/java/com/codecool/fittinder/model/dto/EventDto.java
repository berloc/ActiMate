package com.codecool.fittinder.model.dto;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class EventDto {

    private User user;
    private String name;
    private String location;
    private Integer participants;
    private Date startDate;
    private String description;
    private Interest interest;
    private Status status;

}
