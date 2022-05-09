package com.busbooking;

import com.busbooking.Auth.Domain.UsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CommandLineRunnerStartup implements CommandLineRunner {
    private AuthService authService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //TODO: this can be from the docker
        authService.save(
                new UsersEntity("admin@admin.admin", bCryptPasswordEncoder.encode("admin"), "Test", "Test", true)
        );

    }
}
