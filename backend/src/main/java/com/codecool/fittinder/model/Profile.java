package com.codecool.fittinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @NotNull
    private User user;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profile_interest",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "interest_id"}))
    private List<Interest>interestList;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String location;

    public Profile(User user) {
        this.user = user;
    }
}
