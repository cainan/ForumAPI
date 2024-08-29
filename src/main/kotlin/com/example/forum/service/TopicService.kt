package com.example.forum.service

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.mapper.TopicFormMapper
import com.example.forum.mapper.TopicViewMapper
import com.example.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topicList: ArrayList<Topic> = ArrayList(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    fun list(): List<TopicView> {
        return topicList.map { topic ->
            topicViewMapper.map(topic)
        }
    }

    fun findById(id: Long): TopicView {
        return topicViewMapper.map(topicList.first {
            it.id == id
        })
    }

    fun add(newTopicForm: NewTopicForm) {
        topicList.add(
            topicFormMapper.map(newTopicForm).apply { id = topicList.size + 1L }
        )
    }

    fun update(updateTopicForm: UpdateTopicForm) {
        val first = topicList.first {
            it.id == updateTopicForm.id
        }
        topicList.remove(first)
        val edited = first.copy(title = updateTopicForm.title, message = updateTopicForm.message)
        topicList.add(edited)
    }

    fun remove(id: Long) {
        topicList.remove(topicList.first {
            it.id == id
        })
    }

}