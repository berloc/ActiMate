package com.codecool.fittinder.service.event;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    UserService userService;

    @Autowired
    ProfileRepository profileRepository;


    public List<User> getCommonUsers(String location, Interest interest, Principal principal) {
        List<Interest> interests = new ArrayList<>();
        interests.add(interest);
        Profile me = profileRepository.findById(userService.findByUsername(principal.getName()).getProfile().getId());
        List<Profile> profiles = profileRepository.findByLocationAndInterestListAndSubscribed(location, interests, true);
        profiles.removeIf(profile -> profile == me);
        return profiles.stream().map(profile -> userService.findByProfile(profile)).collect(Collectors.toList());
    }
}