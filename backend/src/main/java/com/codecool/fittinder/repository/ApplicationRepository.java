package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Application;
import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    Application findByEvent(Event event);

    Application findByApplicant(User user);
}
