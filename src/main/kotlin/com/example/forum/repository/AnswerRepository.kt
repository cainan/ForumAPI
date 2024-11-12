package com.example.forum.repository

import com.example.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : JpaRepository<Answer, Long>