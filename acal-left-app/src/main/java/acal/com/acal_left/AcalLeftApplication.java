package acal.com.acal_left;

import acal.com.acal_left.ui.boostrap.SplashScreenBootstrap;
import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	"acal.com.acal_left",           // App module
	"acal.com.acal_left.ui",        // UI module
	"acal.com.acal_left.core",      // Core module
	"acal.com.acal_left.resouces"   // Persistence module
})
public class AcalLeftApplication {

	public static void main(String[] args) {
		SplashScreenBootstrap.showSplash();

		FlatLightLaf.setup();
		new SpringApplicationBuilder(AcalLeftApplication.class)
				.headless(false)
				.run(args);
	}

}
