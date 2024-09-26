package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractValidator<R> implements Validator<R> {

    private static final Logger logger = LogManager.getLogger(AbstractValidator.class);

    protected R request;

    protected AbstractValidator(R request) {
        this.request = request;
    }

    @Override
    public void setRequest(R request) {
        this.request = request;
    }

    protected <E extends ValidationException> boolean executePredicate(boolean condition, Class<E> exception, Object... args) {
        try{
            if (!condition) exception.getDeclaredConstructors()[0].newInstance(args);
            return true;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex){
            logger.error(ex.getMessage());
            return false;
        }
    }
}
