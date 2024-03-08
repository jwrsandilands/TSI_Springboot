package com.tsiexample.sakila.repositories;

import com.tsiexample.sakila.entities.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Short> {

    @Query(value="SELECT * FROM actor WHERE first_name = :firstName LIMIT 10", nativeQuery = true)
    List<Actor> findByFirstName(String firstName);

    @Query(value="SELECT * FROM actor WHERE last_name = :lastName LIMIT 10", nativeQuery = true)
    List<Actor> findByLastName(String lastName);
}
