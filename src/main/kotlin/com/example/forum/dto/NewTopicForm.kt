package com.example.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val title: String,
    @field:NotEmpty
    val message: String,
    @field:NotNull
    val idCourse: Long,
    @field:NotNull
    val idAuthor: Long,
)