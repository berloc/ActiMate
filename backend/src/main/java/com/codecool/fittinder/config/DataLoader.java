package com.codecool.fittinder.config;


import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    InterestRepository interestRepository;

    private List<String> interestList = new ArrayList<String>() {{
        add("waterSports");
        add("winterSports");
        add("outdoorSports");
        add("cultural");
        add("outdoorActivity");
        add("ballGames");
        add("boardGames");
        add("eSports");
        add("indoorSports");
        add("other");
    }};

    @PostConstruct
    public void createInterest() {
        if (interestRepository.findAll().isEmpty()) {
            interestList.forEach(name -> {
                Interest interest = new Interest(name);
                interestRepository.save(interest);
            });
        }
    }

}
