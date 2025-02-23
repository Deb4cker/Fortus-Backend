package com.pmnato.fortus.config;

import java.util.ArrayList;
import java.util.Arrays;

import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.entity.Equipment;
import com.pmnato.fortus.entity.Training;
import com.pmnato.fortus.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pmnato.fortus.commons.constants.Profiles.TEST_PROFILE;
import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.utils.Encryptor;

import lombok.AllArgsConstructor;

@Configuration
@Profile(TEST_PROFILE)
@AllArgsConstructor
public class TestConfiguration implements CommandLineRunner
{
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;
    private final TrainingExerciseRepository trainingExerciseRepository;

    private static final Logger logger = LogManager.getLogger(TestConfiguration.class);

    @Override
    public void run(String... args) {
        generateFakeCategories();
        generateFakeExercises();
        generateFakeEquipments();
        generateFakeUsers();
        generateFakeTrainings();
        generateFakeTrainingExercises();
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
        userRepository.saveAll(Arrays.asList(testUsers));
        logger.info("Fake users saved successfully.");
    }

    private void generateFakeTrainings(){
        logger.info("Generating fake trainings...");
    }
    private void generateFakeCategories(){
        logger.info("Generating fake categories...");
    }
    private void generateFakeEquipments(){
        logger.info("Generating fake equipments...");
    }
    private void generateFakeExercises(){
        logger.info("Generating fake exercises...");
    }
    private void generateFakeTrainingExercises(){
        logger.info("Generating fake training exercises...");
    }
}
