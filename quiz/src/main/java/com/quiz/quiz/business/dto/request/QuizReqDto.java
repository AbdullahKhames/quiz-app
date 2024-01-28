package com.quiz.quiz.business.dto.request;

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
public class QuizReqDto {
    private String title;
    @Builder.Default
    private Set<Long> questionIds = new HashSet<>();
}
