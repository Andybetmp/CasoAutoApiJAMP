package org.example.StepDefinition;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import org.example.Questions.ResponseField;
import org.example.Questions.ResponseListNotEmpty;
import org.example.Tasks.GetResource;
import org.example.Tasks.PostResource;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FakeStoreStepDefinition {
    public static Logger LOGGER = LoggerFactory.getLogger(FakeStoreStepDefinition.class);
    private String endpoint;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint {string}")
    public void elActorEstableceElEndpoint(Actor actor, String endpoint) {
        this.endpoint = endpoint;
        actor.whoCan(CallAnApi.at("https://fakestoreapi.com"));
    }

    @When("el {actor} realiza una solicitud GET")
    public void elActorRealizaUnaSolicitudGET(Actor actor) {
        actor.attemptsTo(
                GetResource.fromEndpoint(endpoint)
        );
    }

    @When("el {actor} crea un producto con {string} {string} {string} {string} {string}")
    public void elActorCreaUnProductoCon(Actor actor, String title, String price, String description, String category, String image) {
        Map<String, Object> producto = new HashMap<>();
        producto.put("title", title);
        producto.put("price", Double.parseDouble(price));
        producto.put("description", description);
        producto.put("category", category);
        producto.put("image", image);

        theActorInTheSpotlight().attemptsTo(
                PostResource.toEndpoint(endpoint, producto)
        );
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(
                seeThat("el codigo de respuesta", ResponseCode.getStatus(), equalTo(responseCode))
        );
    }

    @And("la lista de resultados no debe estar vacia")
    public void laListaDeResultadosNoDebeEstarVacia() {
        theActorInTheSpotlight().should(
                seeThat("la lista de resultados no esta vacia", ResponseListNotEmpty.check(), is(true))
        );
    }

    @And("el campo {string} de la respuesta debe ser {string}")
    public void elCampoDeLaRespuestaDebeSer(String field, String expectedValue) {
        theActorInTheSpotlight().should(
                seeThat("el campo " + field, ResponseField.valueOf(field), equalTo(expectedValue))
        );
    }
}
