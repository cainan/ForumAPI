package com.example.forum.mapper

import com.example.forum.dto.TopicView
import com.example.forum.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicViewMapper : Mapper<Topic, TopicView> {

    override fun map(t: Topic): TopicView {
        return TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            topicStatus = t.status,
            creationDate = t.creationDate
        )
    }
}