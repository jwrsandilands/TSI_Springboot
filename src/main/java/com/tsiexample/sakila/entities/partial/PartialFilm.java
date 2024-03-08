package com.tsiexample.sakila.entities.partial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "film")
@Getter
@Setter
public class PartialFilm {
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
    @JsonIgnore
    private Byte languageId;

    @Column(name = "original_language_id")
    @JsonIgnore
    private Byte originalLanguageId;

    @Column(name = "rental_duration")
    @JsonIgnore
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    @JsonIgnore
    private BigDecimal rentalRate;

    @Column(name = "length")
    @JsonIgnore
    private Short length;

    @Column(name = "replacement_cost")
    @JsonIgnore
    private Float replacementCost;

    @Column(name = "rating")
    @JsonIgnore
    private String rating;

    @Column(name = "special_features")
    @JsonIgnore
    private String specialFeatures;

    @Column(name = "last_update")
    private Date lastUpdate;

    public PartialFilm(){

    }

    public PartialFilm(Short id, String title, String description, Year releaseYear,
                       Byte languageId, Byte originalLanguageId, Byte rentalDuration, BigDecimal rentalRate,
                       Short length, Float replacementCost, String rating, String specialFeatures,
                       Date lastUpdate){
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
}
