package com.tsiexample.sakila.services;

import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.entities.partial.PartialFilm;
import com.tsiexample.sakila.input.ActorInput;
import com.tsiexample.sakila.repositories.ActorRepository;
import com.tsiexample.sakila.repositories.FilmRepository;
import com.tsiexample.sakila.services.abstraction.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ActorService implements IActorService {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    FilmRepository filmRepository;

    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }

    public Actor getActorById(Short id){
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));
    }

    public List<PartialFilm> getFilmsByActorId(Short id){
        final Actor actor =  actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));
        return actor.getFeatures();
    }

    public List<Actor> getActorsByFirstName(String firstName){
        List<Actor> response = actorRepository.findByFirstName(firstName);

        if(response.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "No such actor");
        }

        return response;
    }

    public List<Actor> getActorsByLastName(String lastName){
        List<Actor> response = actorRepository.findByLastName(lastName);

        if(response.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "No such actor");
        }

        return response;
    }

    public Actor createActor(ActorInput actorInput){
        final var actor = new Actor();
        actor.setFirstName(actorInput.getFirstName());
        actor.setLastName(actorInput.getLastName());
        actor.setLastUpdate(new Date());

        for(Short filmId : actorInput.getFilmIds()){
            filmRepository.findById(filmId).ifPresent(film -> actor.getFeatures().add(film.toPartial()));
        }

        return actorRepository.save(actor);
    }

    public Actor updateActor(Short id, ActorInput actorInput){
        final var actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));
        if(actorInput.getFirstName() != null){
            actor.setFirstName(actorInput.getFirstName());
        }
        if(actorInput.getLastName() != null){
            actor.setLastName(actorInput.getLastName());
        }
        return actorRepository.save(actor);
    }

    public Actor addFilm(Short id, ActorInput actorInput){
        final var actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));

        for(Short filmId : actorInput.getFilmIds()){
            filmRepository.findById(filmId).ifPresent(film -> actor.getFeatures().add(film.toPartial()));
        }

        return actorRepository.save(actor);
    }

    public Actor removeFilm(Short id, ActorInput actorInput){
        final var actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));

        for(Short filmId : actorInput.getFilmIds()){
            filmRepository.findById(filmId).ifPresent(film -> actor.getFeatures().remove(film.toPartial()));
        }

        return actorRepository.save(actor);
    }

    public void deleteActor(Short id){
        final var actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No such actor."));
        actorRepository.delete(actor);
    }
}
