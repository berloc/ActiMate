package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.dto.ProfileDto;
import com.codecool.fittinder.model.response.StatusResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/u/profile")
public class ProfileController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping(value = "/me")
    public Profile getProfile(HttpServletRequest request, Principal principal) {
        System.out.println("alma");

        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return getCurrentUser(principal).getProfile();
    }

    @Transactional
    @PostMapping(value = "/{id}")
    public String updateProfile(@PathVariable Integer id, @RequestBody ProfileDto profileDto,
                                              Principal principal, HttpServletRequest request) throws JSONException {

        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        Profile profile = converter.convertToProfile(profileDto);
        User user = getCurrentUser(principal);
        user.setProfile(profile);
        profile.setId(id);
        profileRepository.save(profile);
        return new JSONObject().put("status", "success").toString();
    }

    @Transactional
    @PostMapping(value = "/{id}/subscribe")
    public StatusResponse subscribe(@PathVariable Integer id, @RequestBody Boolean bool) {
        if (profileRepository.findById(id) != null) {
            Profile profile = profileRepository.findById(id);
            profile.setSubscribed(bool);
            profileRepository.save(profile);
            return new StatusResponse("success");
        }
        return new StatusResponse("fail");
    }

    // todo add unsubscribe url to the email templates, write method here, which handles that request

}
