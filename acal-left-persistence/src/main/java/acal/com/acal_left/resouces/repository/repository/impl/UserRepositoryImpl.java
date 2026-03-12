package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.LoginAttempt;
import acal.com.acal_left.core.model.User;
import acal.com.acal_left.core.repository.UserRepository;
import acal.com.acal_left.resouces.repository.model.UserEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByUsername(LoginAttempt attempt) {
        return userJpaRepository.findByUsername(attempt.getUsername())
                .map(UserRepositoryImpl::toEntity);
    }

    public static User toEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public static UserEntity fromEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}


