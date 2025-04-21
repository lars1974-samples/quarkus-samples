package dk.laj.quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Objects;

@Provider
public class HeaderFilter implements ContainerRequestFilter {
    Logger LOG = LoggerFactory.getLogger(HeaderFilter.class);

    @Inject
    RequestContext requestContext;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String apiKey = Objects.requireNonNullElse(requestContext.getHeaders().getFirst("api-key"), "no_value");
        MDC.put("apiKey", apiKey);
        this.requestContext.apiKey = apiKey;
        LOG.info("Headers: {}", requestContext.getHeaders());

    }
}
