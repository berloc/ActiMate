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
        add("climbing");
        add("tennis");
        add("water sports");
        add("winter sports");
        add("outdoor sports");
        add("cultural");
        add("outdoor activity");
        add("ball games");
        add("board games");
        add("eSports");
        add("indoor sports");
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
