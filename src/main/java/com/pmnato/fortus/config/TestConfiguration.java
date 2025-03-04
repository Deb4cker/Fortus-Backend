package com.pmnato.fortus.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.pmnato.fortus._enum.Difficulty;
import com.pmnato.fortus.entity.*;
import com.pmnato.fortus.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.pmnato.fortus._enum.DayOfWeek;

import static com.pmnato.fortus.commons.constants.Profiles.TEST_PROFILE;

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

        var users = userRepository.findAll();
        if (users.isEmpty()) {
            logger.warn("No users found! Unable to generate trainings.");
            return;
        }

        var training = new Training[]{
                new Training(null, "Treino de Peito e Tríceps", LocalDate.now().plusDays(5), true, 3600000L, DayOfWeek.MONDAY, users.get(0), new ArrayList<>()),
                new Training(null, "Treino de Pernas e Glúteos", LocalDate.now().plusDays(1), true, 4500000L, DayOfWeek.TUESDAY, users.get(1), new ArrayList<>()),
                new Training(null, "Treino de Costas e Bíceps", LocalDate.now().plusDays(2), false, 4000000L, DayOfWeek.WEDNESDAY, users.get(2), new ArrayList<>()),
                new Training(null, "Treino de Cardio e Resistência", LocalDate.now().plusDays(3), true, 3000000L, DayOfWeek.THURSDAY, users.get(3), new ArrayList<>()),
                new Training(null, "Treino Funcional", LocalDate.now().plusDays(4), false, 3500000L, DayOfWeek.FRIDAY, users.get(4), new ArrayList<>())
        };

        trainingRepository.saveAll(Arrays.asList(training));

        logger.info("Fake trainings saved successfully.");
    }
    private void generateFakeCategories(){
        logger.info("Generating fake categories...");
        var categories = new Category[]{
                new Category(null, "Treino de Força", "https://image.com/forca.jpg", new ArrayList<>()),
                new Category(null, "Treino Cardiovascular", "https://image.com/cardio.jpg",new ArrayList<>()),
                new Category(null, "Treino Funcional", "https://image.com/funcional.jpg", new ArrayList<>()),
                new Category(null, "Treino de Resistência", "https://image.com/resistencia.jpg", new ArrayList<>()),
                new Category(null, "Treino de Flexibilidade", "https://image.com/flexibilidade.jpg", new ArrayList<>())
        };
        categoryRepository.saveAll(Arrays.asList(categories));
    }
    private void generateFakeEquipments(){
        logger.info("Generating fake equipments...");

        var exercises = exerciseRepository.findAll();
        if (exercises.isEmpty()) {
            logger.warn("No exercises found! Unable to generate equipments.");
            return;
        }

        var equipments = new Equipment[]{
                new Equipment(null, "Halteres 10kg", 10.0, "https://image.com/halteres10.jpg", exercises.get(0)),
                new Equipment(null, "Barra Olímpica 20kg", 20.0, "https://image.com/barra20.jpg", exercises.get(1)),
                new Equipment(null, "Kettlebell 16kg", 16.0, "https://image.com/kettlebell16.jpg", exercises.get(2)),
                new Equipment(null, "Faixa de Resistência", 0.5, "https://image.com/faixa.jpg", exercises.get(3)),
                new Equipment(null, "Roda de Abdominal", 1.5, "https://image.com/roda.jpg", exercises.get(4))
        };

        equipmentRepository.saveAll(Arrays.asList(equipments));

        logger.info("Fake equipments saved successfully.");
    }
    private void generateFakeExercises(){
        logger.info("Generating fake exercises...");

        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            logger.warn("No categories found! Unable to generate exercises.");
            return;
        }

        var exercise = new Exercise[]{
                new Exercise(null, "Supino Reto", Difficulty.MEDIUM, 10, "40s", "https://image.com/supino.jpg", "https://video.com/supino.mp4", new ArrayList<>(), new ArrayList<>(), categories.get(0)),
                new Exercise(null, "Agachamento Livre", Difficulty.HARD, 12, "45s", "https://image.com/agachamento.jpg", "https://video.com/agachamento.mp4", new ArrayList<>(), new ArrayList<>(), categories.get(2)),
                new Exercise(null, "Levantamento de Copo", Difficulty.EASY, 1, "10s", "https://image.com/prancha.jpg", "https://video.com/prancha.mp4", new ArrayList<>(), new ArrayList<>(), categories.get(3)),
                new Exercise(null, "Rosca Direta", Difficulty.MEDIUM, 15, "30s", "https://image.com/rosca.jpg", "https://video.com/rosca.mp4", new ArrayList<>(), new ArrayList<>(), categories.get(1)),
                new Exercise(null, "Flexão de Olho", Difficulty.EASY, 20, "25s", "https://image.com/flexao.jpg", "https://video.com/flexao.mp4", new ArrayList<>(), new ArrayList<>(), categories.get(4)) //Para ser fácil =)
        };

        exerciseRepository.saveAll(Arrays.asList(exercise));

        logger.info("Fake exercises saved successfully.");
    }
    private void generateFakeTrainingExercises(){
        logger.info("Generating fake training exercises...");

        var trainings = trainingRepository.findAll();
        var exercises = exerciseRepository.findAll();

        if (trainings.isEmpty() || exercises.isEmpty()) {
            logger.warn("No trainings or exercises found! Unable to generate training exercises.");
            return;
        }

        var trainingExercises = new ArrayList<TrainingExercise>();

        for (int i = 0; i < trainings.size(); i++) {
            var training = trainings.get(i);
            var exercise = exercises.get(i % exercises.size());

            trainingExercises.add(new TrainingExercise(null, training, exercise));
        }

        trainingExerciseRepository.saveAll(trainingExercises);

        logger.info("Fake training exercises saved successfully.");

    }

}
