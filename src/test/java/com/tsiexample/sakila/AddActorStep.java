package com.tsiexample.sakila;

import com.tsiexample.sakila.controllers.ActorController;
import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.input.ActorInput;
import com.tsiexample.sakila.services.ActorService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class AddActorStep {
    private ActorService mockActorService;
    private final Short expectedId = 1;
    private final Actor expectedActor = new Actor(
            expectedId,
            "NEW",
            "ACTOR",
            new Date()
    );
    private final ActorInput input = new ActorInput(
            expectedActor.getFirstName(),
            expectedActor.getLastName(),
            null
    );
    private Actor returnedActor;

    @Before
    public void setUp(){
        mockActorService = mock(ActorService.class);
    }

    @Given("Actor is Created")
    public void addActorToDataBase(){
        doReturn(expectedActor).when(mockActorService).createActor(input);
    }

    @When("get request is made for the Actor")
    public void whenRequestWithId(){
        final ActorController actorController = new ActorController(mockActorService);
        try{
            returnedActor = actorController.createActor(input);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("the created Actor is Returned")
    public void thenActorReturned(){
        assertNotNull(returnedActor);
        assertEquals(expectedActor.getFirstName(), returnedActor.getFirstName());
        assertEquals(expectedActor.getLastName(), returnedActor.getLastName());
    }
}
