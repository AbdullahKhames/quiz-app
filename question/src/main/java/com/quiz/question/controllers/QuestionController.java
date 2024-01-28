package com.quiz.question.controllers;

import com.quiz.question.business.dto.request.QuestionReqDto;
import com.quiz.question.business.dto.response.QuestionRespDto;
import com.quiz.question.business.service.QuestionService;
import com.quiz.question.globals.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("questions")
@Slf4j
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping
    public ResponseEntity<ResponseDto<QuestionRespDto>> save(@RequestBody QuestionReqDto questionReqDto){
        return new ResponseEntity<>(questionService.save(questionReqDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<QuestionRespDto>> update(@PathVariable long id, @RequestBody QuestionReqDto questionReqDto){
        return ResponseEntity.ok(questionService.update(id, questionReqDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<QuestionRespDto>> delete(@PathVariable long id){
        return ResponseEntity.ok(questionService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<QuestionRespDto>> get(@PathVariable long id){
        return ResponseEntity.ok(questionService.get(id));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getAll(){
        return ResponseEntity.ok(questionService.getAll());
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getByCategory(@PathVariable String category){
        return ResponseEntity.ok(questionService.getByCategory(category));
    }
    @GetMapping("/category/{category}/difficulty/{difficulty}")
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getByCategoryAndDifficulty(@PathVariable String category, @PathVariable String difficulty){
        return ResponseEntity.ok(questionService.getByCategoryAndDifficulty(category, difficulty));
    }
    @GetMapping("/category/{category}/random/{number}")
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getRandomQuestionsByCategory(@PathVariable int number, @PathVariable String category){
        return ResponseEntity.ok(questionService.getRandomQuestionsByCategory(number, category));
    }
    @GetMapping("/category/{category}/difficulty/{difficulty}/random/{number}")
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getRandomQuestionsByCategoryAndDifficulty(@PathVariable int number, @PathVariable String category, @PathVariable String difficulty){
        return ResponseEntity.ok(questionService.getRandomQuestionsByCategoryAndDifficulty(number, category, difficulty));
    }
    @GetMapping("/random/{number}")
    public ResponseEntity<ResponseDto<Collection<QuestionRespDto>>> getRandomQuestions(@PathVariable int number){
        return ResponseEntity.ok(questionService.getRandomQuestions(number));
    }

}
