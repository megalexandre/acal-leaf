package acal.com.acal_left.ui.routes;

import acal.com.acal_left.ui.event.ChangeScreenEvent;
import acal.com.acal_left.ui.event.Screen;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ScreenManager {

    private final ApplicationEventPublisher eventPublisher;

    public ScreenManager(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void changeScreen(Screen screen) {
        eventPublisher.publishEvent(new ChangeScreenEvent(this, screen));
    }

}
