package com.example.forum.controller

import com.example.forum.dto.NewTopicForm
import com.example.forum.model.Answer
import com.example.forum.service.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearerAuth")
class AnswerController(
    private val answerService: AnswerService,
) {

    @PostMapping
    fun save(
        @RequestBody @Valid  answer: Answer) = answerService.save(answer)
}