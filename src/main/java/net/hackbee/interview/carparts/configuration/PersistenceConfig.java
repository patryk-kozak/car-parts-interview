package net.hackbee.interview.carparts.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Just to point where we should look for persistence context
 * Mostly for tests, can be moved to test sources
 */
@Configuration
@ComponentScan(basePackages = "net.hackbee.interview.carparts.persistence")
public class PersistenceConfig {

}
