package acal.com.acal_left;

import acal.com.acal_left.ui.boostrap.SplashScreenBootstrap;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AcalLeftApplication {

	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		SplashScreenBootstrap.showSplash();

		new SpringApplicationBuilder(AcalLeftApplication.class)
				.headless(false)
				.run(args);
	}

}
