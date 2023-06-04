package com.example.demo.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {

            User alex = new User("Alex", "alex@example.com", LocalDate.of(2002, 4, 23));
            User briana = new User("Briana", "Briana@example.com", LocalDate.of(2002, 4, 23));

            repository.saveAll(
                    List.of(alex, briana)
            );
        };
    }
}


