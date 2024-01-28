package com.quiz.question.business.mappers;

import com.quiz.question.business.dto.request.QuestionOptionReqDto;
import com.quiz.question.business.dto.response.QuestionOptionRespDto;
import com.quiz.question.dal.models.QuestionOption;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
        })
public interface QuestionOptionMapper {
    @Mappings({

    })
    QuestionOption reqDtoToEntity(QuestionOptionReqDto questionReqDto);
    List<QuestionOption> reqDtoToEntity(List<QuestionOptionReqDto> questionReqDto);
    Set<QuestionOption> reqDtoToEntity(Set<QuestionOptionReqDto> questionReqDtos);

    QuestionOptionRespDto entityToRespDto(QuestionOption question);

    Set<QuestionOptionRespDto> entityToRespDto(Set<QuestionOption> questions);

    Set<QuestionOptionRespDto> entityToRespDto(List<QuestionOption> questions);

    @InheritConfiguration
    void update(@MappingTarget QuestionOption question, QuestionOptionReqDto questionReqDto);
}
