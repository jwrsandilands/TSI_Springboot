package com.tsiexample.sakila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsiexample.sakila.entities.partial.PartialActor;
import com.tsiexample.sakila.entities.partial.PartialFilm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @Column(name = "language_id")
    private Byte languageId;

    @Column(name = "original_language_id")
    private Byte originalLanguageId;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private Float replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private List<PartialActor> cast = new ArrayList<>();

    public Film(){
    }

    public Film(Short id, String title, String description,
                Year releaseYear, byte languageId, byte originalLanguageId,
                byte rentalDuration, BigDecimal rentalRate, short length,
                Float replacementCost, String rating, String specialFeatures,
                Date lastUpdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
    }

    public PartialFilm toPartial(){
        return new PartialFilm(
                id,
                title,
                description,
                releaseYear,
                languageId,
                originalLanguageId,
                rentalDuration,
                rentalRate,
                length,
                replacementCost,
                rating,
                specialFeatures,
                lastUpdate
        );
    }
}
