package dk.laj.quarkus.weather;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/weather")
@RegisterRestClient(configKey="weather-api")
public interface WeatherClient {

    @GET
    @Path("/precise-weather/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    Weather getPreciseWeather(@PathParam("city") String city);

    @GET
    @Path("/fast-weather")
    @Produces(MediaType.APPLICATION_JSON)
    Weather getFastWeather(@QueryParam("city") String city);
}