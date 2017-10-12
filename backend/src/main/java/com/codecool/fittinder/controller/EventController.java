package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.dto.EventDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/u")
public class EventController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @PostMapping(value = "/event/create")
    @ResponseBody
    public String createEvent(@RequestBody @Valid EventDto eventDto, HttpServletRequest request, Principal principal) throws JSONException {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        JSONObject response = new JSONObject().put("status", "fail");
        Event event = converter.convertToEvent(eventDto, principal);
        eventRepository.save(event);
        return response.toString();
    }

    @GetMapping(value = "/events")
    @ResponseBody
    public List<Event> getAllEvents(HttpServletRequest request, Principal principal) {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        logger.info("All events of user: {} have been collected", principal.getName());
        return eventRepository.findByUserOrderByStartDate(getCurrentUser(principal));
    }

    @GetMapping(value = "/event/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        return eventRepository.findById(id);
    }

    @Transactional
    @PostMapping(value = "/event/{id}")
    @ResponseBody
    public String updateEvent(@PathVariable(value = "id") Integer id, HttpServletRequest request, Principal principal,
                              @RequestBody EventDto eventDto) throws JSONException {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        Event currentEvent = converter.convertToEvent(eventDto, principal);
        currentEvent.setId(id);
        eventRepository.save(currentEvent);
        return new JSONObject().put("status", "success").toString();
    }
}
