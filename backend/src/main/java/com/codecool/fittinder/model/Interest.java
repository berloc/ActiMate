package com.codecool.fittinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "interestList",  cascade=CascadeType.ALL)
    private List<Profile>profileList;


    public Interest(String name) {
        this.name = name;
    }
}
