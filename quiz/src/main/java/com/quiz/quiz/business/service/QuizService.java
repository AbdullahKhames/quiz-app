package com.quiz.quiz.business.service;

import com.quiz.quiz.business.dto.request.QuestionAnswer;
import com.quiz.quiz.business.dto.request.QuizReqDto;
import com.quiz.quiz.business.dto.response.AnswerResponses;
import com.quiz.quiz.business.dto.response.QuizRespDto;
import com.quiz.quiz.globals.ResponseDto;

import java.util.List;

public interface QuizService {
    ResponseDto<QuizRespDto> save(QuizReqDto quizReqDto);
    ResponseDto<QuizRespDto> get(Long id);
    ResponseDto<AnswerResponses> submitQuiz(Long id, List<QuestionAnswer> questionAnswers);
}
