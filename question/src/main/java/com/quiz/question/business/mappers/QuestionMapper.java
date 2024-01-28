package com.quiz.question.business.mappers;

import com.quiz.question.business.dto.request.QuestionReqDto;
import com.quiz.question.business.dto.response.QuestionRespDto;
import com.quiz.question.dal.models.Question;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = {
                QuestionOptionMapper.class,
        })
public interface QuestionMapper {
    @Mappings({

    })
    Question reqDtoToEntity(QuestionReqDto questionReqDto);

    Set<Question> reqDtoToEntity(Set<QuestionReqDto> questionReqDtos);

    QuestionRespDto entityToRespDto(Question question);

    Set<QuestionRespDto> entityToRespDto(Set<Question> questions);

    Set<QuestionRespDto> entityToRespDto(List<Question> questions);

    @Mappings({
        @Mapping(target = "questionOptions", ignore = true),
    })
    void update(@MappingTarget Question question, QuestionReqDto questionReqDto);
}