package com.codecool.fittinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Getter @Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
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

    private Boolean subscribed;


    public Profile() {
        this.subscribed = true;
    }

    public Profile(List<Interest> interestList, String firstName, String lastName, String telephoneNumber, String location) {
        this.interestList = interestList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.location = location;
    }
}
