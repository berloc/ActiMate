package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.dto.EventDto;
import com.codecool.fittinder.model.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/u")
public class EventController extends AbstractController {


    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Transactional
    @PostMapping(value = "/event/create")
    public StatusResponse createEvent(@RequestBody @Valid EventDto eventDto, HttpServletRequest request, Principal principal) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        Event event = converter.convertToEvent(eventDto, principal);
        eventRepository.save(event);
        emailController.sendEventEmail(event.getLocation(), event.getInterest(), principal);
        return new StatusResponse("success");
    }

    @GetMapping(value = "/events")
    public List<Event> getAllEvents(HttpServletRequest request, Principal principal) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        logger.info("All events of user: {} have been collected", principal.getName());
        return eventRepository.findByUserOrderByStartDate(getCurrentUser(principal));
    }

    @GetMapping(value = "/event/{id}")
    public Event getEvent(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return eventRepository.findById(id);
    }

    @Transactional
    @PostMapping(value = "/event/{id}")
    public StatusResponse updateEvent(@PathVariable(value = "id") Integer id, HttpServletRequest request,
                                      Principal principal, @RequestBody @Valid EventDto eventDto) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        Event currentEvent = converter.convertToEvent(eventDto, principal);
        currentEvent.setId(id);
        return new StatusResponse("success");
    }

    @GetMapping(value = "/events/all")
    public List<Event> getAllEvents(HttpServletRequest request) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return eventRepository.findAll();
    }

    @GetMapping(value = "/events/current")
    public List<Event> getActiveEvents(HttpServletRequest request) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return eventRepository.findByStartDateAfter(new Date());
    }
}
