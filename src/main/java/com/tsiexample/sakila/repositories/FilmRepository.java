package com.tsiexample.sakila.repositories;

import com.tsiexample.sakila.entities.Actor;
import com.tsiexample.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Short> {
    @Query(value="SELECT * FROM film WHERE title LIKE :title LIMIT 10", nativeQuery = true)
    List<Film> findByTitle(String title);
}
