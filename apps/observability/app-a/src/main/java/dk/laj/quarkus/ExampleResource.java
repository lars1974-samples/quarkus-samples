package dk.laj.quarkus;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.ExecutionException;


@Path("/hello")
public class ExampleResource {
    ExampleService exampleService;
    @Inject
    ExampleResource(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @Inject
    WeatherService weatherService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Weather hello() throws ExecutionException, InterruptedException {

    return weatherService.getWatherSync();

    }






}
