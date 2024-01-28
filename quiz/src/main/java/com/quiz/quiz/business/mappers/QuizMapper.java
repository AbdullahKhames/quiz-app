package com.quiz.quiz.business.mappers;

import com.quiz.quiz.business.dto.request.QuizReqDto;
import com.quiz.quiz.business.dto.response.QuizRespDto;
import com.quiz.quiz.dal.models.Quiz;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
        })
public interface QuizMapper {
    @Mappings({

    })
    Quiz reqDtoToEntity(QuizReqDto quizReqDto);
    List<Quiz> reqDtoToEntity(List<QuizReqDto> quizReqDtos);
    Set<Quiz> reqDtoToEntity(Set<QuizReqDto> quizReqDtos);

    QuizRespDto entityToRespDto(Quiz quiz);

    Set<QuizRespDto> entityToRespDto(Set<Quiz> quizzes);

    Set<QuizRespDto> entityToRespDto(List<Quiz> quizzes);

    @InheritConfiguration
    void update(@MappingTarget Quiz quiz, QuizReqDto quizReqDto);
}
