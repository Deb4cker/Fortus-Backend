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

    protected <E extends ValidationException> boolean executePredicate(boolean condition, Class<E> exception, Object... args) {
        try{
            if (!condition){

                Constructor<E> constructor = exception.getConstructor(
                        Arrays.stream(args)
                                .map(Object::getClass)
                                .toArray(Class[]::new)
                );

                throw constructor.newInstance(args);
            }

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex){
            logger.error(ex.getMessage());
        }

        return condition;
    }
}
