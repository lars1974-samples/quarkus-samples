package dk.laj.quarkus;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClassWithLogging {
   // Logger logger = LoggerFactory.getLogger(ClassWithLogging.class);

    Logger logger = LogManager.getLogger(ClassWithLogging.class);

    public void doSomething() {
        logger.info("Doing something");
    }
}
