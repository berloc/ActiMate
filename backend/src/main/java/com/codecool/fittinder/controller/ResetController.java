package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.UserPasswordReset;
import com.codecool.fittinder.model.response.StatusResponse;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
public class ResetController extends AbstractController{


    // todo create join table user <=> password reset request with valid field after 10 minutes switch it to false
    @PostMapping("reset/get")
    public StatusResponse getRequest(@RequestParam(name = "user") String username) {
        User user = userService.findByUsername(username);
        String token = tokenService.generateString();
        UserPasswordReset userPasswordReset = createPasswordReset(user, token);
        emailController.sendResetEmail(user, userPasswordReset);
        return new StatusResponse("success");
    }

    @PostMapping("reset/change")
    public StatusResponse changePassword(@RequestBody String password, Principal principal) {
        User user = getCurrentUser(principal);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return new StatusResponse("success");
    }

    private UserPasswordReset createPasswordReset(User user, String token) {
        UserPasswordReset userPasswordReset = new UserPasswordReset();
        userPasswordReset.setUser(user);
        userPasswordReset.setToken(token);
        userPasswordReset.setCreated(new Date());
        userPasswordResetRepository.save(userPasswordReset);
        return userPasswordReset;
    }
}
