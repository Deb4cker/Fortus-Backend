package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.not_found.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_USER;
import static com.pmnato.fortus.commons.constants.EntityName.USER;

@RestControllerAdvice
public class NotFoundExceptionHandler extends ApplicationExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return treatedResponse(ex, CAPITALIZED_USER + BASE_MESSAGE);
    }
    /*
        @ExceptionHandler(UserDataNotFoundException.class)
        public ResponseEntity<Object> handleUserDataNotFoundException(UserNotFoundException ex) {
            return treatedResponse(ex, "User Data" + BASE_MESSAGE);
        }

        @ExceptionHandler(ExerciseNotFoundException.class)
        public ResponseEntity<Object> handleExerciseNotFoundException(UserNotFoundException ex) {
            return treatedResponse(ex, "Exercise" + BASE_MESSAGE);
        }

        @ExceptionHandler(TrainingNotFoundException.class)
        public ResponseEntity<Object> handleTrainingNotFoundException(TrainingNotFoundException ex) {
            return treatedResponse(ex, "Training" + BASE_MESSAGE);
        }

        @ExceptionHandler(EquipmentNotFoundException.class)
        public ResponseEntity<Object> handleEquipmentNotFoundException(UserNotFoundException ex) {
            return treatedResponse(ex, "Equipment" + BASE_MESSAGE);
        }
    */
    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(
                exception.getMessage(),
                cause,
                HttpStatus.NOT_FOUND);
    }

    private static final String BASE_MESSAGE = " does not exists.";
}
