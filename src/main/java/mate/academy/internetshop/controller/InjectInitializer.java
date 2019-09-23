package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InjectInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Dependency injection started...");
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            logger.fatal("Dependency injection failed!" + e);
            throw new RuntimeException(e);
        }
    }
}
