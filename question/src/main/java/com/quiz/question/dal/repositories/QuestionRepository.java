package com.quiz.question.dal.repositories;

import com.quiz.question.dal.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findAllByCategoryIgnoreCase(String category);

    Set<Question> findByCategoryIgnoreCaseAndDifficultyIgnoreCase(String category, String difficulty);

    @Query(value = "SELECT q FROM Question q WHERE upper(q.category) = upper(:category) ORDER BY FUNCTION('RANDOM') LIMIT :number")
    Set<Question> getRandomQuestionsByCategory(@Param("number") int number, @Param("category") String category);

    @Query(value = "SELECT q FROM Question q WHERE upper(q.category) = upper(:category) AND upper(q.difficulty) = upper(:difficulty) ORDER BY FUNCTION('RANDOM')  LIMIT :number")
    Set<Question> getRandomQuestionsByCategoryAndDifficulty(@Param("number") int number, @Param("category") String category, @Param("difficulty") String difficulty);

    @Query(value = "SELECT q FROM Question q ORDER BY FUNCTION('RANDOM')  LIMIT ?1")
    Set<Question> getRandomQuestions(@Param("number") int number);
    @Query(value = "SELECT q.id FROM Question q WHERE upper(q.category) = upper(:category) ORDER BY FUNCTION('RANDOM') LIMIT :number")
    Set<Long> getRandomQuestionsByCategoryForQuiz(@Param("number") int number, @Param("category") String category);

    @Query(value = "SELECT q.id FROM Question q WHERE upper(q.category) = upper(:category) AND upper(q.difficulty) = upper(:difficulty) ORDER BY FUNCTION('RANDOM')  LIMIT :number")
    Set<Long> getRandomQuestionsByCategoryAndDifficultyForQuiz(@Param("number") int number, @Param("category") String category, @Param("difficulty") String difficulty);

    @Query(value = "SELECT q.id FROM Question q ORDER BY FUNCTION('RANDOM')  LIMIT ?1")
    Set<Long> getRandomQuestionsForQuiz(@Param("number") int number);
}