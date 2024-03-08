package com.tsiexample.sakila.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
public class ActorInput {
    @Length(min = 0, max = 50)
    private String firstName;
    @Length(min = 0, max = 50)
    private String lastName;
    private List<Short> filmIds;
}
