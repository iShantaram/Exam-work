package com.example.exam2.service;

import com.example.exam2.exception.NotEnoughQuestionsInServiceError;
import com.example.exam2.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private Random random;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        if (questionService.getAll().size() < amount) {
            try {
                throw new NotEnoughQuestionsInServiceError("Not enough questions in " + questionService);
            } catch (NotEnoughQuestionsInServiceError e) {
                throw new RuntimeException(e);
            }
        } else {
            while (questions.size() < amount) {
                questions.add(questionService.getRandomQuestion());
            }
        }
        return questions;
    }
}
