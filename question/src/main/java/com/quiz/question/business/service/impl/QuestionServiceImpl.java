package com.quiz.question.business.service.impl;

import com.quiz.question.business.dto.request.QuestionAnswer;
import com.quiz.question.business.dto.request.QuestionReqDto;
import com.quiz.question.business.dto.response.AnswerResponse;
import com.quiz.question.business.dto.response.AnswerResponses;
import com.quiz.question.business.dto.response.QuestionRespDto;
import com.quiz.question.business.mappers.QuestionMapper;
import com.quiz.question.business.mappers.QuestionOptionMapper;
import com.quiz.question.business.service.QuestionService;
import com.quiz.question.dal.models.Question;
import com.quiz.question.dal.models.QuestionOption;
import com.quiz.question.dal.repositories.QuestionOptionRepository;
import com.quiz.question.dal.repositories.QuestionRepository;
import com.quiz.question.exceptions.ObjectNotFoundException;
import com.quiz.question.globals.ResponseDto;
import com.quiz.question.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuestionOptionMapper questionOptionMapper;
    private final QuestionOptionRepository questionOptionRepository;
    @Override
    @Transactional
    public ResponseDto<QuestionRespDto> save(QuestionReqDto questionReqDto) {
        return ResponseBuilder.saved(
                questionMapper.entityToRespDto(
                        questionRepository.save(
                                questionMapper.reqDtoToEntity(questionReqDto))));
    }

    @Override
    @Transactional
    public ResponseDto<QuestionRespDto> update(long id, QuestionReqDto questionReqDto) {
        Question question = getQuestion(id);
        questionMapper.update(question, questionReqDto);
        Set<QuestionOption> questionOptions = questionReqDto.getQuestionOptions()
                .stream()
                .filter(Objects::nonNull)
                .map(opt -> {
                    if (opt.getId() != null) {
                        return questionOptionRepository.findById(opt.getId())
                                .orElseGet(() -> questionOptionRepository.save(QuestionOption.builder()
                                        .answer(opt.getAnswer())
                                        .build()));
                    }
                    return questionOptionRepository.save(opt);
                })
                .collect(Collectors.toSet());
        question.getQuestionOptions().clear();
        question.getQuestionOptions().addAll(questionOptions);
        return ResponseBuilder.updated(
                questionMapper.entityToRespDto(
                        question)
        );
    }

    @Override
    @Transactional
    public ResponseDto<QuestionRespDto> delete(long id) {
        Question question = getQuestion(id);
        questionRepository.delete(question);
        return ResponseBuilder.deleted();
    }

    @Override
    public ResponseDto<QuestionRespDto> get(long id) {
        return ResponseBuilder.retrieved(
                questionMapper.entityToRespDto(
                        getQuestion(id)
                ));
    }

    @Override
    public Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Question with id "+ id +" is not found"));
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getAll() {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.findAll()
                ));
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getByCategory(String category) {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.findAllByCategoryIgnoreCase(category)
                )
        );
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getByCategoryAndDifficulty(String category, String difficulty) {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.findByCategoryIgnoreCaseAndDifficultyIgnoreCase(category, difficulty)
                )
        );
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getRandomQuestionsByCategory(int number, String category) {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.getRandomQuestionsByCategory(number,category)
                )
        );
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getRandomQuestionsByCategoryAndDifficulty(int number, String category, String difficulty) {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.getRandomQuestionsByCategoryAndDifficulty(number, category, difficulty)
                )
        );
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getRandomQuestions(int number) {
        return ResponseBuilder.retrievedAll(
                questionMapper.entityToRespDto(
                        questionRepository.getRandomQuestions(number)
                )
        );
    }

    @Override
    public ResponseDto<Collection<Long>> getRandomQuestionsByCategoryForQuiz(int number, String category) {
        return ResponseBuilder.retrievedAll(
                questionRepository.getRandomQuestionsByCategoryForQuiz(number,category)
        );
    }

    @Override
    public ResponseDto<Collection<Long>> getRandomQuestionsByCategoryAndDifficultyForQuiz(int number, String category, String difficulty) {
        return ResponseBuilder.retrievedAll(
                questionRepository.getRandomQuestionsByCategoryAndDifficultyForQuiz(number,category, difficulty)
        );
    }

    @Override
    public ResponseDto<Collection<Long>> getRandomQuestionsForQuiz(int number) {
        return ResponseBuilder.retrievedAll(
                questionRepository.getRandomQuestionsForQuiz(number)
        );
    }

    @Override
    public ResponseDto<Collection<QuestionRespDto>> getQuestionsByIds(List<Long> ids) {
        return ResponseBuilder.retrievedAll(
                questionRepository.findAllById(ids)
                        .stream()
                        .map(questionMapper::entityToRespDto)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public ResponseDto<AnswerResponses> calculateAnswers(List<QuestionAnswer> questionAnswers) {
        AtomicInteger correctAnswers = new AtomicInteger(0);
        Set<AnswerResponse> answerResponses = questionAnswers
                .stream()
                .map(questionAnswer -> {
                    Question question = questionRepository.findById(questionAnswer.getId())
                            .orElseThrow(() -> new ObjectNotFoundException("question not found with given id " + questionAnswer.getId()));
                    AnswerResponse answerResponse = questionMapper.createAnswerResponse(question, questionAnswer);
                    if (answerResponse.isCorrect()){
                        correctAnswers.incrementAndGet();
                    }
                    return answerResponse;
                })
                .collect(Collectors.toSet());
        AnswerResponses answers = AnswerResponses.builder()
                .answerResponses(answerResponses)
//                .score(calculateScore(answerResponses))
                .score((float) correctAnswers.get() / answerResponses.size())
                .build();
        return ResponseBuilder.retrieved(answers);
    }

    private float calculateScore(Set<AnswerResponse> answerResponses) {
        return (float) getCorrectCount(answerResponses) / answerResponses.size();
    }

    private static long getCorrectCount(Set<AnswerResponse> answerResponses) {
        return answerResponses.stream().filter(AnswerResponse::isCorrect).count();
    }
}
