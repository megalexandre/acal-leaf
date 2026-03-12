package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.core.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(LoginAttempt attempt);
}
