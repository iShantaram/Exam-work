package com.example.exam2.exception;

public class NotEnoughQuestionsInServiceError extends Throwable {

    public NotEnoughQuestionsInServiceError(String message) {
        super(message);
    }
}
