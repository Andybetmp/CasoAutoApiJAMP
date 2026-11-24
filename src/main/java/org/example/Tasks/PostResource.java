package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

public class PostResource implements Task {
    private final String endpoint;
    private final Object body;

    public PostResource(String endpoint, Object body) {
        this.endpoint = endpoint;
        this.body = body;
    }

    public static PostResource toEndpoint(String endpoint, Object body) {
        return Instrumented.instanceOf(PostResource.class).withProperties(endpoint, body);
    }

    @Override
    @Step("{0} realiza una peticion POST al endpoint #endpoint")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint).with(
                        request -> request
                                .contentType(ContentType.JSON)
                                .body(body)
                )
        );
    }
}
