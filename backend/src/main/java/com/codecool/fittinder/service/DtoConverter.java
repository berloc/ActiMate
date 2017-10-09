package com.codecool.fittinder.service;

import com.codecool.fittinder.model.dto.EventDto;
import com.codecool.fittinder.model.dto.UserDto;
import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Status;
import com.codecool.fittinder.repository.InterestRepository;
import com.codecool.fittinder.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DtoConverter {

    @Autowired
    private UserService userService;

    private InterestRepository interestRepository;

    public User convertToUser(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword());
    }

    public Event convertToEvent(EventDto eventDto, Principal principal) {
        return new Event(userService.findByUsername(principal.getName()), eventDto.getName(), eventDto.getLocation(), eventDto.getParticipants(),
                eventDto.getStartDate(), eventDto.getDescription(),  eventDto.getInterest(), Status.ACTIVE);
    }
}
