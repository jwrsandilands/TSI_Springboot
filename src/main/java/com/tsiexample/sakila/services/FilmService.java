package com.tsiexample.sakila.services;

import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.entities.Film;
import com.tsiexample.sakila.entities.partial.PartialActor;
import com.tsiexample.sakila.input.FilmInput;
import com.tsiexample.sakila.repositories.ActorRepository;
import com.tsiexample.sakila.repositories.FilmRepository;
import com.tsiexample.sakila.services.abstraction.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FilmService implements IFilmService {
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    ActorRepository actorRepository;

    public List<Film> getFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmById( Short id){
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));
    }

    public List<PartialActor> getCastByFilmId(Short id){
        final Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));
        return film.getCast();
    }

    public List<Film> getFilmsByTitle(String title){
        List<Film> response = filmRepository.findByTitle("%" + title + "%");

        if(response.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "No such film");
        }

        return response;
    }

    public Film createFilm(FilmInput filmInput){
        final Film film = new Film();
        film.setTitle(filmInput.getTitle());
        film.setDescription(filmInput.getDescription());
        film.setLanguageId(filmInput.getLanguageId());
        film.setRentalDuration(filmInput.getRentalDuration());
        film.setRentalRate(filmInput.getRentalRate());
        film.setReplacementCost(filmInput.getReplacementCost());
        film.setLastUpdate(new Date());

        if(filmInput.getActorIds() != null){
            for(Short actorId : filmInput.getActorIds()){
                actorRepository.findById(actorId).ifPresent(actor -> film.getCast().add(actor.toPartial()));
            }
        }

        return filmRepository.save(film);
    }

    public Film updateFilm(Short id, FilmInput filmInput){
        final var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));
        if(filmInput.getTitle() != null){
            film.setTitle(filmInput.getTitle());
        }
        if(filmInput.getDescription() != null){
            film.setDescription(filmInput.getDescription());
        }
        if(filmInput.getLanguageId() != null){
            film.setLanguageId(filmInput.getLanguageId());
        }
        if(filmInput.getRentalDuration() != null){
            film.setRentalDuration(filmInput.getRentalDuration());
        }
        if(filmInput.getRentalRate() != null){
            film.setRentalRate(filmInput.getRentalRate());
        }
        if(filmInput.getReplacementCost() != null){
            film.setReplacementCost(filmInput.getReplacementCost());
        }
        return filmRepository.save(film);
    }

    public Film addActorsToFilm(Short id, FilmInput filmInput) {
        final var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));

        for(Short actorId : filmInput.getActorIds()){
            final var actor = actorRepository.findById(actorId)
                    .orElse(null);
            if(actor != null){
                film.getCast().add(actor.toPartial());
            }
        }

        return filmRepository.save(film);
    }

    public Film removeActorsFromFilm(Short id, FilmInput filmInput) {
        final var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));

        for(Short actorId : filmInput.getActorIds()){
            actorRepository.findById(actorId).ifPresent(actor -> film.getCast().remove(actor.toPartial()));
        }

        return filmRepository.save(film);
    }

    public HttpStatus deleteFilm(Short id){
        final var film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such film."));
        filmRepository.delete(film);
        return HttpStatus.NO_CONTENT;
    }

}
