package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByUser(User user);
}
