
package com.pmnato.fortus.config;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.pmnato.fortus._enum.Difficulty;
import com.pmnato.fortus._enum.DayOfWeek;
import com.pmnato.fortus.entity.*;
import com.pmnato.fortus.repository.*;
import com.pmnato.fortus.utils.Encryptor;

import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.pmnato.fortus.commons.constants.Profiles.TEST_PROFILE;

@Configuration
@Profile(TEST_PROFILE)
@AllArgsConstructor
public class TestConfiguration implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(TestConfiguration.class);

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;
    private final ExerciseEquipmentRepository exerciseEquipmentRepository;
    private final TrainingExerciseRepository trainingExerciseRepository;

    private record ExercisePrescription(
            String exerciseName,
            int sets,
            int exerciseOrder
    ) {}

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            logger.info("Test data already exists. Skipping generation.");
            return;
        }

        var categories = generateCategories();
        var exercises = generateExercises(categories);
        var equipments = generateEquipments();

        generateExerciseEquipments(exercises, equipments);
        generateUsers();

        var trainings = generateTrainings();
        generateTrainingExercises(trainings, exercises);
    }

    private Map<String, Category> generateCategories() {

        logger.info("Generating categories...");

        var categories = List.of(
                new Category(null, "Peito", "https://images.unsplash.com/photo-1534438327276-14e5300c3a48", new ArrayList<>()),
                new Category(null, "Costas", "https://images.unsplash.com/photo-1583454110551-21f2fa2afe61", new ArrayList<>()),
                new Category(null, "Pernas", "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b", new ArrayList<>()),
                new Category(null, "Ombros", "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e", new ArrayList<>()),
                new Category(null, "Braços e Abdômen", "https://images.unsplash.com/photo-1517836357463-d25dfeac3438", new ArrayList<>())
        );

        Objects.requireNonNull(categories);
        var savedCategories = categoryRepository.saveAll(categories);

        logger.info("{} categories generated.", savedCategories.size());

        return savedCategories.stream()
                .collect(Collectors.toMap(category -> Objects.requireNonNull(category).getName(), Function.identity()));
    }

    private Map<String, Exercise> generateExercises(Map<String, Category> categories) {

        logger.info("Generating exercises...");

        var exercises = List.of(

                // CHEST

                new Exercise(
                        null, "Supino Reto com Barra",
                        Difficulty.MEDIUM, 10, "90s",
                        null, "https://www.youtube.com/watch?v=WPM5e8Xv69A",
                        new ArrayList<>(), new ArrayList<>(), categories.get("Peito")
                ),

                new Exercise(
                        null, "Supino Inclinado com Halteres",
                        Difficulty.MEDIUM, 12, "90s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Peito")
                ),

                new Exercise(
                        null, "Crucifixo com Halteres",
                        Difficulty.EASY, 12, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Peito")
                ),

                new Exercise(
                        null, "Crossover na Polia",
                        Difficulty.MEDIUM, 15, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Peito")
                ),

                // BACK

                new Exercise(
                        null, "Puxada Frontal",
                        Difficulty.EASY, 12, "90s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Costas")
                ),

                new Exercise(
                        null, "Remada Curvada com Barra",
                        Difficulty.HARD, 10, "90s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Costas")
                ),

                new Exercise(
                        null, "Remada Baixa na Polia",
                        Difficulty.MEDIUM, 12, "75s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Costas")
                ),

                // LEGS

                new Exercise(
                        null, "Agachamento Livre",
                        Difficulty.HARD, 10, "120s",
                        null, "https://www.youtube.com/embed/R2dMsNhN3DE",
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                new Exercise(
                        null, "Leg Press 45 Graus",
                        Difficulty.MEDIUM, 12, "90s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                new Exercise(
                        null, "Cadeira Extensora",
                        Difficulty.EASY, 15, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                new Exercise(
                        null, "Mesa Flexora",
                        Difficulty.EASY, 12, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                new Exercise(
                        null, "Levantamento Terra Romeno",
                        Difficulty.HARD, 10, "120s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                new Exercise(
                        null, "Panturrilha em Pé",
                        Difficulty.EASY, 15, "45s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Pernas")
                ),

                // SHOULDERS

                new Exercise(
                        null, "Desenvolvimento com Halteres",
                        Difficulty.MEDIUM, 10, "90s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Ombros")
                ),

                new Exercise(
                        null, "Elevação Lateral com Halteres",
                        Difficulty.EASY, 15, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Ombros")
                ),

                // ARMS AND ABS

                new Exercise(
                        null, "Rosca Direta com Barra W",
                        Difficulty.MEDIUM, 12, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Braços e Abdômen")
                ),

                new Exercise(
                        null, "Rosca Martelo com Halteres",
                        Difficulty.EASY, 12, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Braços e Abdômen")
                ),

                new Exercise(
                        null, "Tríceps na Polia com Corda",
                        Difficulty.EASY, 15, "60s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Braços e Abdômen")
                ),

                new Exercise(
                        null, "Abdominal na Polia",
                        Difficulty.MEDIUM, 15, "45s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Braços e Abdômen")
                ),

                new Exercise(
                        null, "Prancha Abdominal",
                        Difficulty.EASY, 1, "45s",
                        null, null,
                        new ArrayList<>(), new ArrayList<>(), categories.get("Braços e Abdômen")
                )
        );

        Objects.requireNonNull(exercises);
        var savedExercises = exerciseRepository.saveAll(exercises);

        logger.info("{} exercises generated.", savedExercises.size());

        return savedExercises.stream()
                .collect(Collectors.toMap(
                        exercise -> Objects.requireNonNull(exercise).getName(),
                        Function.identity()));
    }

    private Map<String, Equipment> generateEquipments() {

        logger.info("Generating equipments...");

        var equipments = List.of(

                new Equipment(null, "Barra Olímpica", 20.0,
                        "https://images.unsplash.com/photo-1534438327276-14e5300c3a48"),

                new Equipment(null, "Anilhas", 5.0,
                        "https://images.unsplash.com/photo-1583454110551-21f2fa2afe61"),

                new Equipment(null, "Halteres", 10.0,
                        "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e"),

                new Equipment(null, "Barra W", 8.0,
                        "https://images.unsplash.com/photo-1534438327276-14e5300c3a48"),

                new Equipment(null, "Banco Reto", 15.0,
                        "https://images.unsplash.com/photo-1534438327276-14e5300c3a48"),

                new Equipment(null, "Banco Regulável", 20.0,
                        "https://images.unsplash.com/photo-1534438327276-14e5300c3a48"),

                new Equipment(null, "Rack de Agachamento", 80.0,
                        "https://images.unsplash.com/photo-1534438327276-14e5300c3a48"),

                new Equipment(null, "Máquina de Pulley", 100.0,
                        "https://images.unsplash.com/photo-1583454110551-21f2fa2afe61"),

                new Equipment(null, "Máquina de Leg Press", 150.0,
                        "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b"),

                new Equipment(null, "Cadeira Extensora", 100.0,
                        "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b"),

                new Equipment(null, "Mesa Flexora", 100.0,
                        "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b"),

                new Equipment(null, "Corda para Tríceps", 1.0,
                        "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e")
        );

        Objects.requireNonNull(equipments);
        var savedEquipments = equipmentRepository.saveAll(equipments);

        logger.info("{} equipments generated.", savedEquipments.size());

        return savedEquipments.stream()
                .collect(Collectors.toMap(
                        equipment -> Objects.requireNonNull(equipment).getName(),
                        Function.identity()));
    }

    private void generateExerciseEquipments(
            Map<String, Exercise> exercises,
            Map<String, Equipment> equipments
    ) {

        logger.info("Associating exercises and equipments...");

        var associations = new ArrayList<ExerciseEquipment>();

        associate(associations, exercises, equipments,
                "Supino Reto com Barra", "Barra Olímpica", "Anilhas", "Banco Reto");

        associate(associations, exercises, equipments,
                "Supino Inclinado com Halteres", "Halteres", "Banco Regulável");

        associate(associations, exercises, equipments,
                "Crucifixo com Halteres", "Halteres", "Banco Reto");

        associate(associations, exercises, equipments,
                "Crossover na Polia", "Máquina de Pulley");

        associate(associations, exercises, equipments,
                "Puxada Frontal", "Máquina de Pulley");

        associate(associations, exercises, equipments,
                "Remada Curvada com Barra", "Barra Olímpica", "Anilhas");

        associate(associations, exercises, equipments,
                "Remada Baixa na Polia", "Máquina de Pulley");

        associate(associations, exercises, equipments,
                "Agachamento Livre", "Barra Olímpica", "Anilhas", "Rack de Agachamento");

        associate(associations, exercises, equipments,
                "Leg Press 45 Graus", "Máquina de Leg Press");

        associate(associations, exercises, equipments,
                "Cadeira Extensora", "Cadeira Extensora");

        associate(associations, exercises, equipments,
                "Mesa Flexora", "Mesa Flexora");

        associate(associations, exercises, equipments,
                "Levantamento Terra Romeno", "Barra Olímpica", "Anilhas");

        associate(associations, exercises, equipments,
                "Desenvolvimento com Halteres", "Halteres", "Banco Regulável");

        associate(associations, exercises, equipments,
                "Elevação Lateral com Halteres", "Halteres");

        associate(associations, exercises, equipments,
                "Rosca Direta com Barra W", "Barra W");

        associate(associations, exercises, equipments,
                "Rosca Martelo com Halteres", "Halteres");

        associate(associations, exercises, equipments,
                "Tríceps na Polia com Corda", "Máquina de Pulley", "Corda para Tríceps");

        associate(associations, exercises, equipments,
                "Abdominal na Polia", "Máquina de Pulley");

        exerciseEquipmentRepository.saveAll(associations);

        logger.info("{} exercise-equipment associations generated.", associations.size());
    }

    private void associate(
            List<ExerciseEquipment> associations,
            Map<String, Exercise> exercises,
            Map<String, Equipment> equipments,
            String exerciseName,
            String... equipmentNames
    ) {

        var exercise = Objects.requireNonNull(
                exercises.get(exerciseName),
                "Exercise not found: " + exerciseName
        );

        for (String equipmentName : equipmentNames) {

            var equipment = Objects.requireNonNull(
                    equipments.get(equipmentName),
                    "Equipment not found: " + equipmentName
            );

            associations.add(new ExerciseEquipment(null, exercise, equipment));
        }
    }

    private void generateUsers() {

        logger.info("Generating users...");

        final String password = "123456";
        var encryptData = Encryptor.getEncryptData(password);

        var users = List.of(
                new User(null, "John", null, "Green", "john@email.com",
                        encryptData.hashedPassword(), encryptData.salt(), new ArrayList<>()),

                new User(null, "Mary", null, "Brown", "mary@email.com",
                        encryptData.hashedPassword(), encryptData.salt(), new ArrayList<>()),

                new User(null, "Paul", null, "Harry", "paul@email.com",
                        encryptData.hashedPassword(), encryptData.salt(), new ArrayList<>()),

                new User(null, "Jessica", null, "Banks", "jessica@email.com",
                        encryptData.hashedPassword(), encryptData.salt(), new ArrayList<>()),

                new User(null, "Kevin", null, "Dalton", "kevin@email.com",
                        encryptData.hashedPassword(), encryptData.salt(), new ArrayList<>())
        );

        Objects.requireNonNull(users);
        userRepository.saveAll(users);

        logger.info("{} users generated.", users.size());
    }

    private List<Training> generateTrainings() {

        logger.info("Generating trainings...");

        var users = userRepository.findAll();

        if (users.size() < 5) {
            logger.warn("Not enough users to generate trainings.");
            return List.of();
        }

        var trainings = List.of(

                new Training(null, "Push Day - Peito, Ombros e Tríceps",
                        scheduledDate(DayOfWeek.MONDAY),
                        true, 3600000L, DayOfWeek.MONDAY,
                        users.get(0), new ArrayList<>()),

                new Training(null, "Pull Day - Costas e Bíceps",
                        scheduledDate(DayOfWeek.TUESDAY),
                        true, 3600000L, DayOfWeek.TUESDAY,
                        users.get(1), new ArrayList<>()),

                new Training(null, "Leg Day - Pernas e Glúteos",
                        scheduledDate(DayOfWeek.WEDNESDAY),
                        true, 4500000L, DayOfWeek.WEDNESDAY,
                        users.get(2), new ArrayList<>()),

                new Training(null, "Upper Body - Parte Superior",
                        scheduledDate(DayOfWeek.THURSDAY),
                        true, 3600000L, DayOfWeek.THURSDAY,
                        users.get(3), new ArrayList<>()),

                new Training(null, "Lower Body - Parte Inferior",
                        scheduledDate(DayOfWeek.FRIDAY),
                        true, 4200000L, DayOfWeek.FRIDAY,
                        users.get(4), new ArrayList<>())
        );

        Objects.requireNonNull(trainings);
        var savedTrainings = trainingRepository.saveAll(trainings);

        logger.info("{} trainings generated.", savedTrainings.size());

        return savedTrainings;
    }

    private LocalDate scheduledDate(DayOfWeek day) {

        return LocalDate.now().with(
                TemporalAdjusters.nextOrSame(
                        java.time.DayOfWeek.valueOf(day.name())
                )
        );
    }

    private void generateTrainingExercises(
            List<Training> trainings,
            Map<String, Exercise> exercises
    ) {

        logger.info("Associating trainings and exercises...");

        if (trainings.size() < 5) {
            logger.warn("Not enough trainings found. Skipping associations.");
            return;
        }

        var associations = new ArrayList<TrainingExercise>();

        // PUSH: Chest, shoulders and triceps
        addExercises(associations, trainings.get(0), exercises,
                new ExercisePrescription("Supino Reto com Barra", 4, 1),
                new ExercisePrescription("Supino Inclinado com Halteres", 3, 2),
                new ExercisePrescription("Crucifixo com Halteres", 3, 3),
                new ExercisePrescription("Desenvolvimento com Halteres", 3, 4),
                new ExercisePrescription("Tríceps na Polia com Corda", 3, 5)
        );

        // PULL: Back and biceps
        addExercises(associations, trainings.get(1), exercises,
                new ExercisePrescription("Puxada Frontal", 4, 1),
                new ExercisePrescription("Remada Curvada com Barra", 3, 2),
                new ExercisePrescription("Remada Baixa na Polia", 3, 3),
                new ExercisePrescription("Rosca Direta com Barra W", 3, 4),
                new ExercisePrescription("Rosca Martelo com Halteres", 3, 5)
        );

        // LEGS: Complete lower body
        addExercises(associations, trainings.get(2), exercises,
                new ExercisePrescription("Agachamento Livre", 4, 1),
                new ExercisePrescription("Leg Press 45 Graus", 3, 2),
                new ExercisePrescription("Cadeira Extensora", 3, 3),
                new ExercisePrescription("Mesa Flexora", 3, 4),
                new ExercisePrescription("Levantamento Terra Romeno", 3, 5),
                new ExercisePrescription("Panturrilha em Pé", 4, 6)
        );

        // UPPER: Upper body
        addExercises(associations, trainings.get(3), exercises,
                new ExercisePrescription("Supino Reto com Barra", 3, 1),
                new ExercisePrescription("Puxada Frontal", 3, 2),
                new ExercisePrescription("Desenvolvimento com Halteres", 3, 3),
                new ExercisePrescription("Elevação Lateral com Halteres", 3, 4),
                new ExercisePrescription("Rosca Direta com Barra W", 3, 5)
        );

        // LOWER: Lower body
        addExercises(associations, trainings.get(4), exercises,
                new ExercisePrescription("Agachamento Livre", 3, 1),
                new ExercisePrescription("Levantamento Terra Romeno", 3, 2),
                new ExercisePrescription("Leg Press 45 Graus", 3, 3),
                new ExercisePrescription("Mesa Flexora", 3, 4),
                new ExercisePrescription("Panturrilha em Pé", 4, 5)
        );

        trainingExerciseRepository.saveAll(associations);

        logger.info("{} training-exercise associations generated.", associations.size());
    }

    private void addExercises(
            List<TrainingExercise> associations,
            Training training,
            Map<String, Exercise> exercises,
            ExercisePrescription... prescriptions
    ) {

        for (var prescription : prescriptions) {

            var exercise = Objects.requireNonNull(
                    exercises.get(prescription.exerciseName()),
                    "Exercise not found: " + prescription.exerciseName()
            );

            associations.add(new TrainingExercise(
                    null,
                    prescription.sets(),
                    prescription.exerciseOrder(),
                    training,
                    exercise
            ));
        }
    }
}
