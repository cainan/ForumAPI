package com.example.forum.dto

import com.example.forum.model.TopicStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val topicStatus: TopicStatus,
    val creationDate: LocalDateTime,
    val editedDate: LocalDate?
) {

}
