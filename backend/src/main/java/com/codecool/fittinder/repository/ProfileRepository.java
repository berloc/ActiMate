package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    List<Profile> findByLocationAndInterestListAndSubscribed(String location, List<Interest> interest, Boolean subscribed);
    Profile findById(Integer id);

}
