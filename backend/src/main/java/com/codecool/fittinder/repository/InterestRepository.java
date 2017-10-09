package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer>{

    Interest findByName(String name);
}
