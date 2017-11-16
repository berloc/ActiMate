package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.response.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ConfirmationController extends AbstractController {

    @GetMapping(value = "/confirm")
    public StatusResponse confirmRegistration(@RequestParam String token, @RequestParam String name) {
        User user = userService.findByUsername(name);
        if (token.equals(userService.findByUsername(name).getConfirmation().getToken())) {
            user.setConfirmed(true);
            userService.save(user);
            return new StatusResponse("success");
        }
        return new StatusResponse("fail");
    }

    @GetMapping(value = "/confirm/re")
    public StatusResponse resendConfirmation(Principal principal) {
//        String template = templateService.createConfirmEmailTemplate(userService.findByUsername(principal.getName()));
//        emailService.sendMessage(principal.getName(), "confirmation", template);
        return new StatusResponse("success");
    }
}
