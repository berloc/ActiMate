package com.codecool.fittinder.repository;

import com.codecool.fittinder.model.Confirmation;
import com.codecool.fittinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Integer> {

    Confirmation findByUser(User user);

    List<Confirmation> findByValid(Boolean isValid);

    Confirmation findByUserAndValid(User user, Boolean valid);
}
