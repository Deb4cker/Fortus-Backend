package com.pmnato.fortus.config;

import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
@AllArgsConstructor
public class TestConfiguration implements CommandLineRunner
{
    private static final Logger logger = LogManager.getLogger(TestConfiguration.class);

    private final UserRepository userRepository;
    //private final CategoryRepository categoryRepository;
    //private final EquipmentRepository equipmentRepository;
    //private final ExercisesRepository exercisesRepository;
    //private final TrainingRepository trainingsRepository;

    private void generateFakeUsers(){
        logger.info("Generating fake users...");

        final String password = "senha_foda";
        List<User> testUsers = List.of(
                new User(null, "John", null, "Green", "john@email.com", password, ""),
                new User(null, "Mary", null, "Brown", "mary@email.com", password, ""),
                new User(null, "Paul", null, "Harry", "paul@email.com", password, ""),
                new User(null, "Jesy", null, "Banfs", "jesy@email.com", password, ""),
                new User(null, "Kali", null, "Dalts", "kali@email.com", password, ""),
                new User(null, "Beny", null, "Doans", "beny@email.com", password, ""),
                new User(null, "Bill", null, "Gatsb", "bill@email.com", password, ""),
                new User(null, "Alle", null, "Fiell", "alle@email.com", password, ""),
                new User(null, "Vlad", null, "Stavs", "vlad@email.com", password, "")
        );

        userRepository.saveAll(testUsers);

        logger.info("Fake users saved successfully.");
    }

    private void generateFakeTrainings(){
        logger.info("Generating fake trainings...");

        //lógica para gerar mock dos treinos

        logger.info("Fake trainings saved successfully.");
    }

    private void generateFakeExercises(){
        logger.info("Generating fake exercises...");

        //lógica para gerar mock dos exercícios

        logger.info("Fake exercises saved successfully.");
    }

    private void generateFakeCategories(){
        logger.info("Generating fake categories...");

        //lógica para gerar mock das categorias

        logger.info("Fake categories saved successfully.");
    }

    private void generateFakeEquipments(){
        logger.info("Generating fake equipments...");

        //lógica para gerar mock dos equipamentos

        logger.info("Fake equipments saved successfully.");
    }

    @Override
    public void run(String... args){
        try {
            generateFakeUsers();
            generateFakeCategories();
            generateFakeEquipments();
            generateFakeExercises();
            generateFakeTrainings();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("All Mocks Generated successfully\n");
        logger.info("✅ Pode ficar tranquilo que rodou Bonitinho ✅");
        logger.info("✅ Pra entrar no banco de dados usa essa rota: http://localhost:8080/h2-console/ ✅");
    }
}
