package com.example.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val author: UserT,
    @ManyToOne
    val topic: Topic,
    val isSolution: Boolean = false
)