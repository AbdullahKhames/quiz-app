package com.quiz.question.business.service;

import com.quiz.question.business.dto.request.QuestionReqDto;
import com.quiz.question.business.dto.response.QuestionRespDto;
import com.quiz.question.dal.models.Question;
import com.quiz.question.globals.ResponseDto;

import java.util.Collection;

public interface QuestionService {
    ResponseDto<QuestionRespDto> save(QuestionReqDto questionReqDto);
    ResponseDto<QuestionRespDto> update(long id, QuestionReqDto questionReqDto);
    ResponseDto<QuestionRespDto> delete(long id);

    ResponseDto<QuestionRespDto> get(long id);
    Question getQuestion(long id);

    ResponseDto<Collection<QuestionRespDto>> getAll();


    ResponseDto<Collection<QuestionRespDto>> getByCategory(String category);

    ResponseDto<Collection<QuestionRespDto>> getByCategoryAndDifficulty(String category, String difficulty);


    ResponseDto<Collection<QuestionRespDto>> getRandomQuestionsByCategory(int number, String category);

    ResponseDto<Collection<QuestionRespDto>> getRandomQuestionsByCategoryAndDifficulty(int number, String category, String difficulty);

    ResponseDto<Collection<QuestionRespDto>> getRandomQuestions(int number);
}
