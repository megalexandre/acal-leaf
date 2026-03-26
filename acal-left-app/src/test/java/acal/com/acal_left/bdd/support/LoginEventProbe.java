package acal.com.acal_left.bdd.support;

import acal.com.acal_left.core.model.User;
import acal.com.acal_left.ui.event.LoginSuccessEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LoginEventProbe {

    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicReference<User> lastUser = new AtomicReference<>();

    @EventListener
    public void onLoginSuccess(LoginSuccessEvent event) {
        successCount.incrementAndGet();
        lastUser.set(event.getUser());
    }

    public int successCount() {
        return successCount.get();
    }

    public User lastUser() {
        return lastUser.get();
    }

    public void reset() {
        successCount.set(0);
        lastUser.set(null);
    }
}

