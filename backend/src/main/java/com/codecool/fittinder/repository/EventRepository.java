package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findById(Integer id);

    List<Event> findByUserOrderByStartDate(User user);

    List<Event> findByStartDateAfter(Date startDate);



}