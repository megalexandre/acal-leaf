package acal.com.acal_left.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class ChangeScreenEvent extends ApplicationEvent {

    private final Screen screen;

    public ChangeScreenEvent(Object source, Screen screen) {
        super(source);
        this.screen = screen;
    }

}
