package dk.laj.quarkus;

import dk.laj.quarkus.weather.WeatherService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


@Path("/city-report")
public class CityReportResource {
    Logger LOG = LoggerFactory.getLogger(CityReportResource.class);

    WeatherService weatherService;

    RequestContext requestContext;

    OperationMetrics operationMetrics;

    @Inject
    CityReportResource(RequestContext requestContext, WeatherService weatherService, OperationMetrics operationMetrics) {
        this.weatherService = weatherService;
        this.requestContext = requestContext;
        this.operationMetrics = operationMetrics;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CityReport cityReport(@QueryParam("city") String city) throws ExecutionException, InterruptedException {
        requestContext.operation = "cityReport";
        LOG.info("CityReportResource: " + city);
        CityReport cityReport = new CityReport(weatherService.getWeatherSync(city));
        operationMetrics.recordOperation();
        return cityReport;
    }
}
