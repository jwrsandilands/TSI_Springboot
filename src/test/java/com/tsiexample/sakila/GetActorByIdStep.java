package com.tsiexample.sakila;

import com.tsiexample.sakila.controllers.ActorController;
import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.services.ActorService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GetActorByIdStep {
    private ActorService mockActorService;
    private final Short expectedId = 1;
    private final Actor expectedActor = new Actor(
            expectedId,
            "PENELOPE",
            "GUINESS",
            new Date()
    );
    private Actor returnedActor;

    @Before
    public void setUp(){
        mockActorService = mock(ActorService.class);
    }

    @Given("Actor with id {short} exists")
    public void givenActorWithIdExists(Short id){
        doReturn(expectedActor).when(mockActorService).getActorById(id);
    }

    @When("get request is made for actor {short}")
    public void whenRequestWithId(Short id){
        final ActorController actorController = new ActorController(mockActorService);
        try{
            returnedActor = actorController.getActorById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("an Actor is returned")
    public void thenActorReturned(){
        assertNotNull(returnedActor);
        assertEquals(expectedActor.getFirstName(), returnedActor.getFirstName());
        assertEquals(expectedActor.getLastName(), returnedActor.getLastName());
    }
}
