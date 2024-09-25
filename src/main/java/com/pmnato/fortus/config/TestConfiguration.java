package com.pmnato.fortus.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pmnato.fortus.commons.constants.Profiles.TEST_PROFILE;
import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.utils.Encryptor;

import lombok.AllArgsConstructor;

@Configuration
@Profile(TEST_PROFILE)
@AllArgsConstructor
public class TestConfiguration implements CommandLineRunner
{
    private final UserRepository repository;
    private static final Logger logger = LogManager.getLogger(TestConfiguration.class);

    @Override
    public void run(String... args) {
        generateFakeUsers();
    }

    private void generateFakeUsers(){
        logger.info("Generating fake users...");
        
        final String password = "senha_foda";
        var testUsers = new User[]{
                new User(null, "John", "john@email.com", password, "", new ArrayList<>()),
                new User(null, "Mary", "mary@email.com", password, "", new ArrayList<>()),
                new User(null, "Paul", "paul@email.com", password, "", new ArrayList<>()),
                new User(null, "Jesy", "jesy@email.com", password, "", new ArrayList<>()),
                new User(null, "Kali", "kali@email.com", password, "", new ArrayList<>()),
                new User(null, "Beny", "beny@email.com", password, "", new ArrayList<>()),
                new User(null, "Bill", "bill@email.com", password, "", new ArrayList<>()),
                new User(null, "Alle", "alle@email.com", password, "", new ArrayList<>()),
                new User(null, "Vlad", "vlad@email.com", password, "", new ArrayList<>())
        };

        for (User user : testUsers){
            var encryptData = Encryptor.getEncryptData(password);
            user.setPassword(encryptData.hashedPassword());
            user.setSalt(encryptData.salt());

            logger.info("User {} generated.", user.getName());
        }

        logger.info("Saving users...");
        repository.saveAll(Arrays.asList(testUsers));
        logger.info("Fake users saved successfully.");
    }
}
