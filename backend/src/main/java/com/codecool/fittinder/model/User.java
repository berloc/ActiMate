package com.codecool.fittinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Date regDate;

    private Boolean confirmed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", unique = true)
    private Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserPasswordReset> resets;

    @JsonIgnore
    @OneToOne
    private Confirmation confirmation;

    // todo add application list set the connection

    public User(String email, String password) {
        this.username = email;
        this.password = password;
        this.confirmed = false;
    }
}
