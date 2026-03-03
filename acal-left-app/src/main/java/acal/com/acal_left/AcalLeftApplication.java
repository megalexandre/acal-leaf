package acal.com.acal_left;

import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	"acal.com.acal_left",
	"acal.com.acal_left.ui",
	"acal.com.acal_left.core",
	"acal.com.acal_left.resouces"
})
@EnableJpaRepositories(basePackages = "acal.com.acal_left.resouces.repository.repository.jpa")
public class AcalLeftApplication {

	public static void main(String[] args) {
		//SplashScreenBootstrap.showSplash();

		FlatDarkLaf.setup();
		new SpringApplicationBuilder(AcalLeftApplication.class)
				.headless(false)
				.run(args);
	}

	/*
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		SplashScreenBootstrap.closeSplash();
	}*/

}
