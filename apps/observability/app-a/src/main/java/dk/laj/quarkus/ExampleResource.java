package dk.laj.quarkus;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class ExampleResource {
    ExampleService exampleService;
    @Inject
    ExampleResource(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        exampleService.doSomething();

        return "Hello from Quarkus REST "+ System.currentTimeMillis();

    }






}
