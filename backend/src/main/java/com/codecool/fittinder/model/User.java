package com.codecool.fittinder.model;

import com.codecool.fittinder.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "´user´")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    @OneToOne
    @JoinColumn
    private Profile profile;
    @Enumerated(value = EnumType.STRING)
    private Enum<Role> role;
    private Date regDate;

}
