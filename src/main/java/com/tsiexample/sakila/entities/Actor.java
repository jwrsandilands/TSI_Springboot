package com.tsiexample.sakila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsiexample.sakila.entities.partial.PartialActor;
import com.tsiexample.sakila.entities.partial.PartialFilm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "actor_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    @ToString.Exclude
    @JsonIgnore
    private List<PartialFilm> features = new ArrayList<>();

    public Actor() {
    }

    public Actor(Short id, String firstName, String lastName, Date lastUpdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public PartialActor toPartial(){
        return new PartialActor(
                id,
                firstName,
                lastName,
                lastUpdate
        );
    }
}
