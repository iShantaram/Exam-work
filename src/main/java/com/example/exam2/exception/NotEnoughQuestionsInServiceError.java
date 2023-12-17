package com.example.exam2.exception;

public class NotEnoughQuestionsInServiceError extends RuntimeException {
    public NotEnoughQuestionsInServiceError(String message) {
        super(message);
    }
}
