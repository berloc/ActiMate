package com.codecool.fittinder.model.request;

import lombok.Data;

@Data
public class ConfirmEmailRequest {

    private String to;

    private String name;

    private String token;

    private String url;

}
