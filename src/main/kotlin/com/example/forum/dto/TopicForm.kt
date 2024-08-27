package com.example.forum.dto

data class TopicForm(
    val title: String,
    val message: String,
    val idCourse: Long,
    val idAuthor: Long,


    ) {
    override fun toString(): String {
        return "TopicDto(title='$title', message='$message', idCourse=$idCourse, idAuthor=$idAuthor)"
    }
}