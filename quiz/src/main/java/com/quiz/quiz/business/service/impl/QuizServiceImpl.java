package com.quiz.quiz.business.service.impl;

import com.quiz.quiz.business.dto.request.QuestionAnswer;
import com.quiz.quiz.business.dto.request.QuizReqDto;
import com.quiz.quiz.business.dto.response.AnswerResponses;
import com.quiz.quiz.business.dto.response.QuizRespDto;
import com.quiz.quiz.business.mappers.QuizMapper;
import com.quiz.quiz.business.service.QuizService;
import com.quiz.quiz.dal.repositories.QuizRepository;
import com.quiz.quiz.globals.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    @Override
    public ResponseDto<QuizRespDto> save(QuizReqDto quizReqDto) {
        return null;
    }

    @Override
    public ResponseDto<QuizRespDto> get(Long id) {
        return null;
    }

    @Override
    public ResponseDto<AnswerResponses> submitQuiz(Long id, List<QuestionAnswer> questionAnswers) {
        return null;
    }
}
