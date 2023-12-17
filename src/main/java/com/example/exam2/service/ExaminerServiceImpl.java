package com.example.exam2.service;

import com.example.exam2.exception.NotEnoughQuestionsInServiceError;
import com.example.exam2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        if (questionService.getAll().size() < amount) {
                throw new NotEnoughQuestionsInServiceError("Not enough questions in question service");
        } else {
            while (questions.size() < amount) {
                Question q = questionService.getRandomQuestion();
                questions.add(q);
            }
        }
        return questions;
    }
}
