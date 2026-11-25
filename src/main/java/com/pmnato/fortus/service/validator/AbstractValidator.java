package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.request.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public abstract class AbstractValidator<R extends Request> implements Validator<R> {

    protected R request;
    protected static final Logger logger = LogManager.getLogger(AbstractValidator.class);

    protected AbstractValidator(R request) {
        this.request = request;
    }

    @Override
    public void setRequest(R request) {
        this.request = request;
    }

    protected boolean requestIsPresent() {
        return executePredicate(
                request != null,
                ValidationException.class,
                "Request must not be null."
        );
    }

    protected <E extends ValidationException> boolean executePredicate(
            boolean condition,
            Class<E> exception,
            Object... args
    ) {
        if (condition) return true;

        try {
            Class<?>[] parameterTypes = Arrays.stream(args)
                    .map(arg -> arg == null ? Object.class : arg.getClass())
                    .toArray(Class<?>[]::new);

            Constructor<E> constructor = exception.getConstructor(parameterTypes);
            throw constructor.newInstance(args);
        } catch (NoSuchMethodException | InvocationTargetException
                | InstantiationException | IllegalAccessException ex) {
            logger.error("Unable to execute validation predicate", ex);
        }

        return false;
    }
}
