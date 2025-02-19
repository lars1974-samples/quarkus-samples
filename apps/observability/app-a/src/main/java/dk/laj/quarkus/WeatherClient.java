package dk.laj.quarkus;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/weather")
@RegisterRestClient(configKey="weather-api")
public interface WeatherClient {

    @GET
    @Path("/great-weather-today")
    @Produces(MediaType.APPLICATION_JSON)
    Weather greatWeatherToday();

    @GET
    @Path("/city-weather")
    @Produces(MediaType.APPLICATION_JSON)
    Weather cityWeather();
}