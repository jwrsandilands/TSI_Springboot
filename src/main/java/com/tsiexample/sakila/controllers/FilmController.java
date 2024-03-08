package com.tsiexample.sakila.controllers;

import com.tsiexample.sakila.entities.Film;
import com.tsiexample.sakila.entities.partial.PartialActor;
import com.tsiexample.sakila.input.FilmInput;
import com.tsiexample.sakila.services.FilmService;
import com.tsiexample.sakila.services.abstraction.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    IFilmService filmService;

    @GetMapping("/films")
    public List<Film> listFilms(){
        return filmService.getFilms();
    }

    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable Short id){
        return filmService.getFilmById(id);
    }

    @GetMapping("/films/{id}/cast")
    public List<PartialActor> getCastByFilmId(@PathVariable Short id){
        return filmService.getCastByFilmId(id);
    }

    @GetMapping("/films/title/{filmTitle}")
    public List<Film> getFilmsByTitle(@PathVariable String filmTitle){
        return filmService.getFilmsByTitle(filmTitle);
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody FilmInput inputData){
        return filmService.createFilm(inputData);
    }

    @PatchMapping("/films/{id}")
    public Film updateFilm(@PathVariable Short id, @RequestBody FilmInput inputData){
        return filmService.updateFilm(id, inputData);
    }

    @PatchMapping("/films/{id}/cast/add")
    public Film addActorsToFilm(@PathVariable Short id, @RequestBody FilmInput inputData) {
        return filmService.addActorsToFilm(id, inputData);
    }

    @PatchMapping("/films/{id}/cast/remove")
    public Film removeActorsFromFilm(@PathVariable Short id, @RequestBody FilmInput inputData) {
        return filmService.removeActorsFromFilm(id, inputData);
    }

    @DeleteMapping("/films/{id}")
    public HttpStatus deleteFilm(@PathVariable Short id){
         return filmService.deleteFilm(id);
    }
}
