package dk.laj.quarkus;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestContext {
    public String apiKey;
    public String operation;
}
