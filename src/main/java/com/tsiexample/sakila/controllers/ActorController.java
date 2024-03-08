package com.tsiexample.sakila.controllers;

import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.entities.partial.PartialFilm;
import com.tsiexample.sakila.input.ActorInput;
import com.tsiexample.sakila.services.ActorService;
import com.tsiexample.sakila.services.abstraction.IActorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class ActorController {

    @Autowired
    IActorService actorService;

    @GetMapping("/actors")
    public List<Actor> listActors(){
        return actorService.getAllActors();
    }

    @GetMapping("/actors/{id}")
    public Actor getActorById(@PathVariable Short id){
        return actorService.getActorById(id);
    }

    @GetMapping("/actors/{id}/features")
    public List<PartialFilm> getFilmsByActorId(@PathVariable Short id){
        return actorService.getFilmsByActorId(id);
    }

    @GetMapping("/actors/firstName/{firstName}")
    public List<Actor> getActorByFirstName(@PathVariable String firstName){
        return actorService.getActorsByFirstName(firstName);
    }

    @GetMapping("/actors/lastName/{lastName}")
    public List<Actor> getActorByLastName(@PathVariable String lastName){
        return actorService.getActorsByLastName(lastName);
    }

    @PostMapping("/actors")
    public Actor createActor(@RequestBody ActorInput inputData){
        return actorService.createActor(inputData);
    }

    @PatchMapping("/actors/{id}")
    public Actor updateActor(@PathVariable Short id, @RequestBody ActorInput inputData){
        return actorService.updateActor(id, inputData);
    }

    @PatchMapping("/actors/{id}/features/add")
    public Actor addFilmsToActor(@PathVariable Short id, @RequestBody ActorInput inputData){
        return actorService.addFilm(id, inputData);
    }

    @PatchMapping("/actors/{id}/features/remove")
    public Actor removeFilmsFromActor(@PathVariable Short id, @RequestBody ActorInput inputData){
        return actorService.removeFilm(id, inputData);
    }

    @DeleteMapping("/actors/{id}")
    public HttpStatus deleteActor(@PathVariable Short id){
        actorService.deleteActor(id);
        return HttpStatus.NO_CONTENT;
    }
}
