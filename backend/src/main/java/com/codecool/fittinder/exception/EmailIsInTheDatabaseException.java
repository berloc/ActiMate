package com.codecool.fittinder.exception;

public class EmailIsInTheDatabaseException extends Exception {

    public EmailIsInTheDatabaseException() {
        super("The email is already in the database");
    }
}
