package com.codecool.fittinder.service.util;

import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.dto.EventDto;
import com.codecool.fittinder.model.dto.ProfileDto;
import com.codecool.fittinder.model.dto.UserDto;
import com.codecool.fittinder.repository.InterestRepository;
import com.codecool.fittinder.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DtoConverterService {

    @Autowired
    private UserService userService;

    @Autowired
    private InterestRepository interestRepository;

    public User convertToUser(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword());
    }

    public Event convertToEvent(EventDto eventDto, Principal principal) {
        return new Event(userService.findByUsername(principal.getName()), eventDto.getName(), eventDto.getLocation(), eventDto.getParticipants(),
                eventDto.getStartDate(), eventDto.getDescription(), interestRepository.findByName(eventDto.getInterest().getName()));
    }

    public Profile convertToProfile(ProfileDto profileDto) {
        return new Profile(profileDto.getInterestList(), profileDto.getFirstName(),
                profileDto.getLastName(), profileDto.getTelephoneNumber(), profileDto.getLocation());
    }
}
