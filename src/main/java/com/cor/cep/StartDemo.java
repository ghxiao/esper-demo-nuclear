package com.cor.cep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cor.cep.util.RandomTemperatureEventGenerator;
import org.springframework.stereotype.Component;

/**
 * Entry point for the Demo. Run this from your IDE, or from the command line using 'mvn exec:java'.
 */
@Component
public class StartDemo {

    /**
     * Logger
     */
    private static Logger LOG = LoggerFactory.getLogger(StartDemo.class);

    private static long noOfTemperatureEvents = 1000;

    /**
     * Main method - start the Demo!
     */
    public static void main(String[] args) throws Exception {

        LOG.debug("Starting...");

        if (args.length != 1) {
            LOG.debug("No override of number of events detected - defaulting to " + noOfTemperatureEvents + " events.");
        } else {
            noOfTemperatureEvents = Long.valueOf(args[0]);
        }

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.cor.cep");
        RandomTemperatureEventGenerator generator = ctx.getBean(RandomTemperatureEventGenerator.class);

        generator.startSendingTemperatureReadings(noOfTemperatureEvents);
    }

}
