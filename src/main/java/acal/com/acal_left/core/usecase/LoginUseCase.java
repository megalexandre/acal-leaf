package acal.com.acal_left.core.usecase;

import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.resouces.model.User;
import acal.com.acal_left.resouces.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUseCase {

    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(LoginAttempt attempt) {
        return userRepository.findByUsernameAndPassword(attempt.username(), attempt.password());
    }

}
