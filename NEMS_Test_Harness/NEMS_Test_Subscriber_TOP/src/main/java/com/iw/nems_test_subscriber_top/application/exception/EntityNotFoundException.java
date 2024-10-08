package com.iw.nems_test_subscriber_top.application.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }

    public EntityNotFoundException(String name, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with the name '" + name + "' does not exist in our records");
    }
}
