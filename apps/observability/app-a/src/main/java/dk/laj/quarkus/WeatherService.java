package dk.laj.quarkus;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class WeatherService {

    @Inject
    @RestClient
    WeatherClient weatherClient;

    @Inject
    ManagedExecutor managedExecutor;

    @WithSpan
    public CompletionStage<Weather> getCityWeather() {
        return managedExecutor.supplyAsync(() -> weatherClient.cityWeather());
    }

    @WithSpan
    public CompletionStage<Weather> getGreatWeather() {
        return managedExecutor.supplyAsync(() -> weatherClient.greatWeatherToday());
    }

    @WithSpan
    public Weather getWatherSync() throws ExecutionException, InterruptedException {
        return getWeather().toCompletableFuture().get();
    }

    @WithSpan
    public CompletionStage<Weather> getWeather() {
        CompletionStage<Weather> weatherToday = getCityWeather();
        CompletionStage<Weather> greatWeather = getGreatWeather();

        CompletionStage<Weather> com =  greatWeather.thenCombine(weatherToday, (v1, v2) -> new Weather((int)((v1.getTemperature() + v2.getTemperature()) / 2.0), (int)((v1.getHumidity() + v2.getHumidity())/2.0)));
        return com;
    }


}
