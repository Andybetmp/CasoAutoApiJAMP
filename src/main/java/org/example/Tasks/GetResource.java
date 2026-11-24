package org.example.Tasks;

import com.github.dockerjava.api.model.TaskStatus;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class GetResource implements Task {
    private final String endpoint;

    public GetResource(String endpoint) {
        this.endpoint = endpoint;
    }

    public static GetResource fromEndpoint(String endpoint) {
        return Instrumented.instanceOf(GetResource.class).withProperties(endpoint);
    }

    @Override
    @Step("{0} realiza una peticion GET al endpoint #endpoint")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint)
        );
    }
}
