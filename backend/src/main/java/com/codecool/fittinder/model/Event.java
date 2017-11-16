package com.codecool.fittinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    private String name;

    private String location;

    private Integer participants;

    private Date startDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interest", referencedColumnName = "name")
    private Interest interest;

    private Boolean applied;

    // todo set application list

    public Event(User user, String name, String location, Integer participants, Date startDate, String description, Interest interest) {
        this.user = user;
        this.name = name;
        this.location = location;
        this.participants = participants;
        this.startDate = startDate;
        this.description = description;
        this.interest = interest;
        this.applied = false;
    }
}
