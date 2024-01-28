package com.quiz.quiz.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizRespDto {
    private Long id;
    private String title;
    @Builder.Default
    private Set<QuestionRespDto> questions = new HashSet<>();
}
