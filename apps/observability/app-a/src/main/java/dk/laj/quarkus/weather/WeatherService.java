package dk.laj.quarkus.weather;

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
    public CompletionStage<Weather> getPreciseWeather(String city) {
        return managedExecutor.supplyAsync(() -> weatherClient.getFastWeather(city));
    }

    @WithSpan
    public CompletionStage<Weather> getFastWeather(String city) {
        return managedExecutor.supplyAsync(() -> weatherClient.getPreciseWeather(city));
    }

    @WithSpan
    public Weather getWeatherSync(String city) throws ExecutionException, InterruptedException {
        return getWeather(city).toCompletableFuture().get();
    }

    @WithSpan
    public CompletionStage<Weather> getWeather(String city) {
        CompletionStage<Weather> preciseStage = getPreciseWeather(city);
        CompletionStage<Weather> fastStage = getFastWeather(city);

        return fastStage.thenCombine(preciseStage, (precise, fast) -> {
            if(precise != null){
                return precise;
            } else {
                return fast;
            }
        });
    }


}
