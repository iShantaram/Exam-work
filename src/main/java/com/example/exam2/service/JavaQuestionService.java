package com.example.exam2.service;

import com.example.exam2.exception.QuestionNotFoundException;
import com.example.exam2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random;

    public JavaQuestionService() {
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        return add(q);
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            throw new QuestionNotFoundException("Not found question for remove");
        }
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int nextInt = random.nextInt(questions.size());
        Question q = questions.stream().toList().get(nextInt);
        if (q == null || questions.isEmpty()) {
            throw new QuestionNotFoundException("Not any question in service");
        }
        return q;
    }
}
