package com.example.exam2.service;

import com.example.exam2.exception.QuestionNotFoundException;
import com.example.exam2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void addStrings_success() {
        //Подготовка входных данных
        String question = "one plus one";
        String answer = "two";

        //Подготовка ожидаемого результата
        Question expectedQuestion = new Question("one plus one", "two");

        //Начало теста
        Question actualQuestion = javaQuestionService.add(question, answer);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void addQuestion_success() {
        //Подготовка входных данных
        //Подготовка ожидаемого результата
        Question expectedQuestion = new Question("one plus one in sale", "three");

        //Начало теста
        Question actualQuestion = javaQuestionService.add(expectedQuestion);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void remove_success() {
        //Подготовка входных данных
        Question question1 = new Question("one plus one", "two");
        Question question2 = new Question("one plus one in sale", "three");

        //Подготовка ожидаемого результата
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);
        Question expectedQuestion = new Question("one plus one in sale", "three");

        //Начало теста
        Question actualQuestion = javaQuestionService.remove(question2);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void remove_withQuestionNotFoundException() {
        //Подготовка входных данных
        Question question1 = new Question("one plus one", "two");
        Question question2 = new Question("one plus one in sale", "three");
        String question = "what's up";
        String answer = "kekwait";
        Question questionForRemove = new Question(question, answer);

        //Подготовка ожидаемого результата
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);
        String expectedMessage = "Not found question for remove";

        //Начало теста
        Exception exception = assertThrows(
                QuestionNotFoundException.class,
                () -> javaQuestionService.remove(questionForRemove)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll() {
        //Подготовка входных данных
        Question question1 = new Question("one plus one", "two");
        Question question2 = new Question("one plus one in sale", "three");
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);

        //Подготовка ожидаемого результата
        Set<Question> expectedQuestions = new HashSet<>();
        expectedQuestions.add(question1);
        expectedQuestions.add(question2);

        //Начало теста
        Collection<Question> actualQuestions = javaQuestionService.getAll();
        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void getRandomQuestion() {
        //Подготовка входных данных
        Question question1 = new Question("one plus one", "two");
        Question question2 = new Question("one plus one in sale", "three");
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);

        //Подготовка ожидаемого результата

        //Начало теста
        Question actualQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(actualQuestion);
        assertTrue(actualQuestion.equals(question1) || actualQuestion.equals(question2));
    }

    @Test
    void getRandomQuestion_withQuestionNotFoundException() {
        //Подготовка входных данных

        //Подготовка ожидаемого результата
        String expectedMessage = "Not any question in service";

        //Начало теста
        Exception exception = assertThrows(
                QuestionNotFoundException.class,
                () -> javaQuestionService.getRandomQuestion()
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}