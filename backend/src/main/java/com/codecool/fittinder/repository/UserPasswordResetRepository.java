package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.UserPasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPasswordResetRepository extends JpaRepository<UserPasswordReset, Integer> {

    List<UserPasswordReset> findByValid(Boolean isValid);
}
