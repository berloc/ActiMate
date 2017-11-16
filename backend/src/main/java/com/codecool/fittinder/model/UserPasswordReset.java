package com.codecool.fittinder.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserPasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    private Boolean valid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private String token;

    public UserPasswordReset() {
        this.valid = true;
    }
}