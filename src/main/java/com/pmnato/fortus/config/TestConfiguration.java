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
                new User(null, "John", null, "Green", "john@email.com", password, "", new ArrayList<>()),
                new User(null, "Mary", null, "Brown", "mary@email.com", password, "", new ArrayList<>()),
                new User(null, "Paul", null, "Harry", "paul@email.com", password, "", new ArrayList<>()),
                new User(null, "Jesy", null, "Banfs", "jesy@email.com", password, "", new ArrayList<>()),
                new User(null, "Kali", null, "Dalts", "kali@email.com", password, "", new ArrayList<>()),
                new User(null, "Beny", null, "Doans", "beny@email.com", password, "", new ArrayList<>()),
                new User(null, "Bill", null, "Gatsb", "bill@email.com", password, "", new ArrayList<>()),
                new User(null, "Alle", null, "Fiell", "alle@email.com", password, "", new ArrayList<>()),
                new User(null, "Vlad", null, "Stavs", "vlad@email.com", password, "", new ArrayList<>())
        };

        for (User user : testUsers){
            var encryptData = Encryptor.getEncryptData(password);
            user.setPassword(encryptData.hashedPassword());
            user.setSalt(encryptData.salt());

            logger.info("User {} {} generated.", user.getFirstName(), user.getLastName());
        }

        logger.info("Saving users...");
        repository.saveAll(Arrays.asList(testUsers));
        logger.info("Fake users saved successfully.");
    }
}
