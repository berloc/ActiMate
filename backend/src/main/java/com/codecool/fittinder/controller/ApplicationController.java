package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.response.StatusResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/u/event")
public class ApplicationController extends AbstractController {

    // todo apply to an event, logged in user click on an event -> detail view of the event -> apply to the event button
    // todo -> it hits the endpoint ~/event/id/apply -> create new Application model -> set the user and the event
    // todo -> send email, that someone try to apply to the event -> the host of the event can finish the application
    // todo -> set applied to true (save) -> send calendar invite to the participants
    // todo chat on the event view

    public StatusResponse apply() {
        return new StatusResponse("success");
    }

}
