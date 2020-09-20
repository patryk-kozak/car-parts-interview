package net.hackbee.interview.carparts;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.hackbee.interview.carparts.persistence")
public class PersistenceConfig {

}
