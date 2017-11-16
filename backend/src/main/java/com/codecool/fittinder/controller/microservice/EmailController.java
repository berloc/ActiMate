package com.codecool.fittinder.controller.microservice;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.UserPasswordReset;
import com.codecool.fittinder.model.request.CommonEventRequest;
import com.codecool.fittinder.model.request.ConfirmEmailRequest;
import com.codecool.fittinder.model.request.ResetEmailRequest;
import com.codecool.fittinder.model.response.AttemptResponse;
import com.codecool.fittinder.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Service
public class EmailController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EventService eventService;

    private static final boolean SHOULD_MOCK = false;
    private static final String APP_URL = "http://localhost:4200";
    private static final String SERVICE_URL = "http://localhost:9090/api/send";

    private AttemptResponse post(String url, Object data) {
        return restTemplate.postForObject(SERVICE_URL + url, data, AttemptResponse.class);
    }

    public AttemptResponse sendConfirmationEmail(User user) {
        if (SHOULD_MOCK) { return new AttemptResponse(); }
        ConfirmEmailRequest request = new ConfirmEmailRequest();
        request.setTo(user.getUsername());
        request.setName(user.getUsername());
//        String url = APP_URL + "/confirm?token=" + user.getConfirmation().getToken() + "&name=" + user.getUsername();
        String url = "http://localhost:8080/api" + "/confirm?token=" + user.getConfirmation().getToken() + "&name=" + user.getUsername();
        request.setUrl(url);
        request.setToken(user.getConfirmation().getToken());
        return post("/confirmation", request);
    }

    public AttemptResponse sendResetEmail(User user, UserPasswordReset reset){
        ResetEmailRequest request = new ResetEmailRequest();
        request.setTo(user.getUsername());
        String url = APP_URL + "/reset?code=" + reset.getToken() + "&user=" + user.getUsername();
        request.setUrl(url);
        return post("/reset", request);
    }

    public AttemptResponse sendEventEmail(String location, Interest interest, Principal principal) {
        CommonEventRequest request = new CommonEventRequest();
        request.setUsers(eventService.getCommonUsers(location, interest, principal));
        request.setInterest(interest.getName());
        return post("/event/new", request);
    }

    public AttemptResponse sendCalendarInvite() {
        return null;
    }

}
