package com.codecool.fittinder.service.util;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.UserPasswordReset;
import com.codecool.fittinder.repository.ConfirmationRepository;
import com.codecool.fittinder.repository.UserPasswordResetRepository;
import com.codecool.fittinder.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TaskSchedulerService {



    @Autowired
    private UserPasswordResetRepository resetRepository;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Autowired
    private UserService userService;

    // kill confirmation after 5 minutes
    @Scheduled(fixedDelay = 300000)
    private void killPasswordReset() {
        List<UserPasswordReset> resets = resetRepository.findByValid(true);
        resets.forEach(reset -> {
            if (new Date().getTime() > reset.getCreated().getTime() + 300000) {
                reset.setValid(false);
                resetRepository.save(reset);
            }
        });
    }

    // kill confirmation after 15 minutes
    @Scheduled(fixedDelay = 600000)
    private void killConfirmation() {
        List<User> users = userService.findByConfirmed(false);
        users.forEach(user -> {
            if (new Date().getTime() > user.getConfirmation().getCreated().getTime() + 600000) {
                System.out.println("taskscheduler" + user);
                confirmationRepository.delete(user.getConfirmation());
            }
        });
    }
}
