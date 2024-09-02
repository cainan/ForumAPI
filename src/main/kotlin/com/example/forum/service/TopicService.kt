package com.example.forum.service

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.exception.NotFoundException
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
        return topicViewMapper.map(topicList.stream().filter {
            it.id == id
        }.findFirst().orElseThrow {
            NotFoundException("Topic not found")
        })
    }

    fun add(newTopicForm: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(newTopicForm).apply { id = topicList.size + 1L }
        topicList.add(topic)
        return topicViewMapper.map(topic)
    }

    fun update(updateTopicForm: UpdateTopicForm): TopicView {
        val first = topicList.stream().filter {
            it.id == updateTopicForm.id
        }.findFirst().orElseThrow {
            NotFoundException("Topic not found")
        }
        topicList.remove(first)
        val edited = first.copy(title = updateTopicForm.title, message = updateTopicForm.message)
        topicList.add(edited)
        return topicViewMapper.map(edited)
    }

    fun remove(id: Long) {
        val topic = topicList.stream().filter {
            it.id == id
        }.findFirst().orElseThrow {
            NotFoundException("Topic not found")
        }
        topicList.remove(
            topic
        )
    }

}