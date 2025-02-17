package dk.laj.quarkus;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.annotations.AddingSpanAttributes;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.CompletableFuture;


@ApplicationScoped
public class ExampleService {

    @Inject
    Tracer tracer;

 @WithSpan(value= "doSomething", kind = SpanKind.SERVER)
    public void doSomething() {
        Span span = tracer.spanBuilder("My custom span")
                .setAttribute("attr", "attr.value")
                .setParent(Context.current().with(Span.current()))
                .setSpanKind(SpanKind.SERVER)
                .startSpan();

        addArgumentToExistingSpan("lajparam");

        System.out.println("Doing something");
        span.end();
    }

    @AddingSpanAttributes
    void addArgumentToExistingSpan(@SpanAttribute(value = "arg") String arg) {

    }

    public CompletableFuture<String> slowCall(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from slowCall";
        });

        return future;

    }
}
