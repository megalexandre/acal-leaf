package acal.com.acal_left;

import acal.com.acal_left.ui.boostrap.SplashScreenBootstrap;
import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AcalLeftApplication {

	public static void main(String[] args) {
		SplashScreenBootstrap.showSplash();
		FlatDarkLaf.setup();

		new SpringApplicationBuilder(AcalLeftApplication.class)
				.headless(false)
				.run(args);
	}

}
