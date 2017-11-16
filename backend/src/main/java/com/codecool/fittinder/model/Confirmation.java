package com.codecool.fittinder.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "confirmation", cascade = CascadeType.ALL)
    private User user;

    private String token;

    private Boolean valid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;


    public Confirmation() {
        this.valid = true;
    }
}
