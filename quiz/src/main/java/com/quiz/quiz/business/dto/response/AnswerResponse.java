package com.quiz.quiz.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private Long id;
    private String answer;
    private String correctAnswer;
    private String explanation;
    private boolean correct;
}
