package com.quiz.question.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRespDto {
    private Long id;
    private String title;
    private Set<QuestionOptionRespDto> questionOptions = new HashSet<>(4);
    private String category;
    private String difficulty;
    private String subCategory;

}
