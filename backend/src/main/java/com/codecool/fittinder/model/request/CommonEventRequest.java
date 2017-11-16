package com.codecool.fittinder.model.request;

import com.codecool.fittinder.model.User;
import lombok.Data;

import java.util.List;

@Data

public class CommonEventRequest {

    private List<User> users;

    private String interest;
}
