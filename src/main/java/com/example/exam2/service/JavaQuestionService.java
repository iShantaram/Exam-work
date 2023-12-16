package com.example.exam2.service;

import com.example.exam2.exception.QuestionNotFoundException;
import com.example.exam2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();

    public JavaQuestionService() {}

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
            throw new QuestionNotFoundException("Not found " + question);
        }
    }

    @Override
    public Collection<Question> getAll() {
        return questions.stream().toList();
    }

    @Override
    public Question getRandomQuestion() {
        int size = questions.size();
        Random random = new Random();
        return new ArrayList<>(getAll()).get(random.nextInt(size));
    }
}
