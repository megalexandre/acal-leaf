package acal.com.acal_left.core.usecase.login;

import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.core.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public LoginUseCase(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Optional<User> login(LoginAttempt attempt) {
        encoder.encode("acal2026");

        return userRepository.findByUsername(attempt)
                .filter(user -> encoder.matches(attempt.getPassword(), user.getPassword()))
                .map(user -> {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    user.setPassword(null);
                    return user;
                });
    }

}
