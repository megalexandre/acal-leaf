package acal.com.acal_left.core.model;


import acal.com.acal_left.shared.StringUtil;

public record LoginAttempt(String username, String password) {

    public LoginAttempt(String username, char[] password) {
        this(username, new String(password));
    }

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
