package dk.laj.quarkus;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Random;


@Path("/weather")
public class WeatherResource {
    Random random = new Random();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/precise-weather/{city}")
    public Weather preciseWeather(@PathParam("city") String city) {
        try {
            Thread.sleep(1000 + random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        random.nextInt(30);
        return new Weather(-10 + random.nextInt(30), random.nextInt(100));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fast-weather")
    public Weather fastWeather(@QueryParam("city") String city) {
        return new Weather(-10 + random.nextInt(30), random.nextInt(100));
    }
}
