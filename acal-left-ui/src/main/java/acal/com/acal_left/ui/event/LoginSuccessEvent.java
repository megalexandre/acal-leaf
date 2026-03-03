package acal.com.acal_left.ui.event;

import acal.com.acal_left.core.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoginSuccessEvent extends ApplicationEvent {

    private final User user;

    public LoginSuccessEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

}

