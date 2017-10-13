package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.dto.ProfileDto;
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
@RequestMapping(value = "u/profile")
public class ProfileController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping
    public Profile getProfile(HttpServletRequest request, Principal principal) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return getCurrentProfile(principal);
    }

    @Transactional
    @PostMapping(value = "/{id}")
    public @ResponseBody String updateProfile(@PathVariable(name = "id") Integer id, @RequestBody ProfileDto profileDto,
                                              Principal principal, HttpServletRequest request) throws JSONException {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        Profile profile = converter.convertToProfile(profileDto, principal);
        profile.setId(id);
        profileRepository.save(profile);
        return new JSONObject().put("status", "success").toString();
    }
}
