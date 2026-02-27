package acal.com.acal_left.ui.boostrap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class SplashScreen {

    JPanel panel1;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        SplashScreenBootstrap.closeSplash();
    }
}
