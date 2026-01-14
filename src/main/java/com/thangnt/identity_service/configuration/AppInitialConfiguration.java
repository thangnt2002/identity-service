package com.thangnt.identity_service.configuration;

import com.thangnt.identity_service.entities.User;
import com.thangnt.identity_service.enums.Role;
import com.thangnt.identity_service.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitialConfiguration {

    @NonFinal
    String ADMIN = "admin";

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner runner(){
        return args -> {
            if(!userRepository.existsByUsername(ADMIN)){
                Set<com.thangnt.identity_service.entities.Role> roles = new HashSet<>();
                roles.add(com.thangnt.identity_service.entities.
                        Role.builder()
                                .name(Role.ADMIN.name())
                                .description("Admin")
                        .build());
                userRepository.save(User.builder()
                                .username(ADMIN)
                                .password(passwordEncoder.encode(ADMIN))
                                .roles(roles)
                        .build());
               log.warn("Admin account was created!!!");
            }
        };
    }

}
