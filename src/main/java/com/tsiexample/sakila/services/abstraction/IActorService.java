package com.tsiexample.sakila.services.abstraction;

import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.entities.Film;
import com.tsiexample.sakila.entities.partial.PartialFilm;
import com.tsiexample.sakila.input.ActorInput;
import java.util.List;

public interface IActorService {
    public List<Actor> getAllActors();
    public Actor getActorById(Short id);
    public List<PartialFilm> getFilmsByActorId(Short id);
    public List<Actor> getActorsByFirstName(String firstName);
    public List<Actor> getActorsByLastName(String lastName);
    public Actor createActor(ActorInput actorInput);
    public Actor updateActor(Short id, ActorInput actorInput);
    public Actor addFilm(Short id, ActorInput actorInput);
    public Actor removeFilm(Short id, ActorInput actorInput);
    public void deleteActor(Short id);
}
