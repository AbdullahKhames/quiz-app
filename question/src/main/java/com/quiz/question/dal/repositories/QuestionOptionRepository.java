package com.quiz.question.dal.repositories;

import com.quiz.question.dal.models.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    Optional<QuestionOption> findByAnswerIgnoreCase(String answer);
}