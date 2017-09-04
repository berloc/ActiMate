package com.codecool.fittinder.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public enum Role {
    ADMIN("Admin"),
    USER("User");

    private String type;
}
