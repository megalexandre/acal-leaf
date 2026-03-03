package acal.com.acal_left.core.model;


import acal.com.acal_left.shared.StringUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginAttempt {

    private final String username;
    private final String password;

    public boolean isValid() {
        return isValid(username) && isValid(password);
    }

    public boolean isNotValid() {
        return !isValid();
    }

    private boolean isValid(String value) {
        return !StringUtil.safe(value).isEmpty();
    }

}
