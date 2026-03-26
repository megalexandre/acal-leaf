package acal.com.acal_left.bdd.config;

import acal.com.acal_left.bdd.support.LoginEventProbe;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.core.repository.UserRepository;
import acal.com.acal_left.core.usecase.login.LoginUseCase;
import acal.com.acal_left.ui.flatlaf.screen.login.LoginScreen;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class LoginBddTestConfig {

    public static final String VALID_USERNAME = "admin";
    public static final String VALID_PASSWORD = "acal123";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository(BCryptPasswordEncoder encoder) {
        String encodedPassword = encoder.encode(VALID_PASSWORD);

        return attempt -> {
            if (attempt == null || !VALID_USERNAME.equals(attempt.getUsername())) {
                return Optional.empty();
            }

            return Optional.of(User.builder()
                    .id(1)
                    .username(VALID_USERNAME)
                    .password(encodedPassword)
                    .build());
        };
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return new LoginUseCase(userRepository, encoder);
    }

    @Bean
    public LoginScreen loginScreen(LoginUseCase loginUseCase, ApplicationEventPublisher eventPublisher) {
        return new LoginScreen(loginUseCase, eventPublisher);
    }

    @Bean
    public LoginEventProbe loginEventProbe() {
        return new LoginEventProbe();
    }
}

