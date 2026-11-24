package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ResponseListNotEmpty implements Question<Boolean> {
    public static ResponseListNotEmpty check() {
        return new ResponseListNotEmpty();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<?> items = SerenityRest.lastResponse().jsonPath().getList("$");
        return items != null && !items.isEmpty();
    }
}
