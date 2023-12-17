package com.example.exam2.service;

import com.example.exam2.exception.NotEnoughQuestionsInServiceError;
import com.example.exam2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    Set<Question> questions = new HashSet<>();
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setup() {
        questions.add(new Question("one plus one", "two"));
        questions.add(new Question("one plus one by sale", "three"));
        questions.add(new Question("50 sale with 50 sale", "75 sale"));
        questions.add(new Question("80 sale with 50 sale", "90 sale"));
    }

    @Test
    void getQuestions_success() {
        //Подготовка входных данных
        int amount = 1;

        //Подготовка ожидаемого результата
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(mockQuestion());
        Collection<Question> expectedQuestions = new HashSet<>(questions);

        //Начало теста
        Collection<Question> actualQuestion = examinerService.getQuestions(amount);
        assertTrue(expectedQuestions.containsAll(actualQuestion));
    }

    private Question mockQuestion() {
        return questions.stream().findAny().get();
    }

    @Test
    void getQuestions_withNotEnoughQuestionsInServiceError() {
        //Подготовка входных данных
        int amount = 5;

        //Подготовка ожидаемого результата
        when(questionService.getAll()).thenReturn(questions);
        String expectedMessage = "Not enough questions in question service";

        //Начало теста
        Exception exception = assertThrows(
                NotEnoughQuestionsInServiceError.class,
                () -> examinerService.getQuestions(amount)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}