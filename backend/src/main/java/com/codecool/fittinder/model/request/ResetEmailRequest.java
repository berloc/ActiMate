package com.codecool.fittinder.model.request;


import lombok.Data;

@Data
public class ResetEmailRequest {

    private String to;

    private String name;

    private String url;
}
