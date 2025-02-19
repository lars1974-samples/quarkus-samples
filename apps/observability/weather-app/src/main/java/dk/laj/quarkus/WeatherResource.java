package dk.laj.quarkus;



import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Random;


@Path("/weather")
public class WeatherResource {
    Random random = new Random();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/great-weather-today")
    public Weather greatWhetherToday() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        random.nextInt(30);
        return new Weather(-10 + random.nextInt(30), random.nextInt(100));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/city-weather")
    public Weather hello() {
        return new Weather(-10 + random.nextInt(30), random.nextInt(100));
    }






}
