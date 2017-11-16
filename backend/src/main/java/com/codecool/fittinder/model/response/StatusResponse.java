package com.codecool.fittinder.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class StatusResponse {

    private String status;

    public StatusResponse(String value) {
        this.status = value;
    }
}
