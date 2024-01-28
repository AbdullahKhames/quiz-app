package com.quiz.question.business.dto.request;

import com.quiz.question.dal.models.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReqDto {
    private String title;
    private Set<QuestionOption> questionOptions = new HashSet<>(4);
    private String correctAnswer;
    private String explanation;
    private String category;
    private String difficulty;
    private String subCategory;

}
