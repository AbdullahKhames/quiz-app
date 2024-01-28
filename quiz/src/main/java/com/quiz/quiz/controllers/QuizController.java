package com.quiz.quiz.controllers;

import com.quiz.quiz.business.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quizzes")
@Slf4j
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

}
