package com.example.forum.model

import com.example.forum.dto.TopicView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Kotlin Basic",
        message = "Learning Kotlin",
        topicStatus = TopicStatus.NOT_ANSWERED,
        creationDate = LocalDateTime.now(),
        editedDate = LocalDate.now()
    )
}