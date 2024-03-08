package com.tsiexample.sakila.input;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

@Data
public class FilmInput {
    private String title;
    private String description;
/*    private Year releaseYear;*/
    private Byte languageId;
/*    private Byte originalLanguageId;*/
    private Byte rentalDuration;
    private BigDecimal rentalRate;
/*    private Short length;*/
    private Float replacementCost;
/*    private String rating;
    private String specialFeatures;*/
    private List<Short> actorIds;
}
