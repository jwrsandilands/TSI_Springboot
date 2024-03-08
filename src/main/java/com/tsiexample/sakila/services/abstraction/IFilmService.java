package com.tsiexample.sakila.services.abstraction;

import com.tsiexample.sakila.entities.Film;
import com.tsiexample.sakila.entities.partial.PartialActor;
import com.tsiexample.sakila.input.FilmInput;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public interface IFilmService {
    public List<Film> getFilms();
    public Film getFilmById(Short id);
    public List<PartialActor> getCastByFilmId(Short id);
    public List<Film> getFilmsByTitle(String title);
    public Film createFilm(FilmInput filmInput);
    public Film updateFilm(Short id, FilmInput filmInput);
    public Film addActorsToFilm(Short id, FilmInput filmInput);
    public Film removeActorsFromFilm(Short id, FilmInput filmInput);
    public HttpStatus deleteFilm(Short id);
}
