package com.pmnato.fortus.config;

import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
@AllArgsConstructor
public class TestConfiguration implements CommandLineRunner
{
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception{
        repository.saveAll(Arrays.asList(
                new User(null, "John", "john@email.com", "senha_foda", ""),
                new User(null, "Mary", "mary@email.com", "senha_foda", ""),
                new User(null, "Paul", "paul@email.com", "senha_foda", ""),
                new User(null, "Jesy", "jesy@email.com", "senha_foda", ""),
                new User(null, "Kali", "kali@email.com", "senha_foda", ""),
                new User(null, "Beny", "beny@email.com", "senha_foda", ""),
                new User(null, "Bill", "bill@email.com", "senha_foda", ""),
                new User(null, "Alle", "alle@email.com", "senha_foda", ""),
                new User(null, "Vlad", "vlad@email.com", "senha_foda", "")
        ));
    }
}
