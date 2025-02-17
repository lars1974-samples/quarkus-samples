package dk.laj.quarkus;


import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExampleResourceTest {
    private LogCaptor logCaptor;

    @BeforeEach
    public void setUp() {
        logCaptor = LogCaptor.forClass(ClassWithLogging.class);
        logCaptor.disableConsoleOutput();
    }

    @AfterEach
    public void tearDown() {
        logCaptor.clearLogs();
    }

    @Test
    void testHelloEndpoint() {
       new ClassWithLogging().doSomething();
        System.out.println(logCaptor.getInfoLogs().size());
    }

}