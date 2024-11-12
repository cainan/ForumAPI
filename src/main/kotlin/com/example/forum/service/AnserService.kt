package com.example.forum.service

import com.example.forum.model.Answer
import com.example.forum.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(private val answerRepository: AnswerRepository) {

    fun save(answer: Answer) = answerRepository.save(answer)
}