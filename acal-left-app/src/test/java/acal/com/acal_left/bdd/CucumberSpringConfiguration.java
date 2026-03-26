package acal.com.acal_left.bdd;

import acal.com.acal_left.bdd.config.LoginBddTestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = LoginBddTestConfig.class)
public class CucumberSpringConfiguration {
}

