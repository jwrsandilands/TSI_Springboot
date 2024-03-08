package com.tsiexample.sakila;

import com.tsiexample.sakila.controllers.FilmController;
import com.tsiexample.sakila.entities.Film;
import com.tsiexample.sakila.services.FilmService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GetFilmByIdStep {
    private FilmService mockFilmService;
    private final Short expectedId = 1;
    private final Film expectedFilm = new Film(
            expectedId,
            "ACADEMY DINOSAUR",
            "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
            Year.parse("2006"),
            (byte)1,
            (byte)0,
            (byte)6,
            new BigDecimal("0.99"),
            (short)86,
            (float)20.99,
            "PG",
            "Deleted Scenes,Behind the Scenes",
            new Date()
    );
    private Film returnedFilm;

    @Before
    public void setUp(){
        mockFilmService = mock(FilmService.class);
    }

    @Given("Film with id {short} exists")
    public void givenFilmWithIdExists(Short id){
        doReturn(expectedFilm).when(mockFilmService).getFilmById(id);
    }

    @When("get request is made for Film {short}")
    public void whenRequestWithId(Short id){
        final FilmController filmController = new FilmController(mockFilmService);
        try{
            returnedFilm = filmController.getFilmById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("a Film is returned")
    public void thenFilmReturned(){
        assertNotNull(returnedFilm);
        assertEquals(expectedId, returnedFilm.getId());
        assertEquals(expectedFilm.getTitle(), returnedFilm.getTitle());
        assertEquals(expectedFilm.getDescription(), returnedFilm.getDescription());
    }
}
