package com.quiz.quiz.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponses {
    private Set<AnswerResponse> answerResponses;
    private float score;
}
