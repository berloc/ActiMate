package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);

    User findByProfile(Profile profile);

    List<User> findByConfirmed(Boolean bool);
}
