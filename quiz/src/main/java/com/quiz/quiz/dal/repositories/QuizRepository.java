package com.quiz.quiz.dal.repositories;

import com.quiz.quiz.dal.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}