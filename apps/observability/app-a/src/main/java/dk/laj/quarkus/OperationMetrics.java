package dk.laj.quarkus;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OperationMetrics {
    RequestContext requestContext;
    MeterRegistry meterRegistry;

    @Inject
    OperationMetrics(RequestContext requestContext, MeterRegistry meterRegistry) {
        this.requestContext = requestContext;
        this.meterRegistry = meterRegistry;
    }

    public void recordOperation() {
        meterRegistry.counter("operation", "operation", requestContext.operation, "apiKey", requestContext.apiKey).increment();
    }
}
