package com.example.forum.service

import com.example.forum.model.Course
import com.example.forum.model.Topic
import com.example.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(private var topicList: List<Topic>) {

    init {
        val topic1 = Topic(
            id = 1,
            title = "Duvida sobre Spring",
            message = "Estou com duvida nessa parte",
            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
        )
        val topic2 = Topic(
            id = 2,
            title = "Duvida sobre Kotlin",
            message = "Estou com duvida nessa parte",
            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
        )
        val topic3 = Topic(
            id = 3,
            title = "Duvida sobre Android",
            message = "Estou com duvida nessa parte",
            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
        )

        topicList = listOf(topic1, topic2, topic3)
    }

    fun list(): List<Topic> {
        return topicList
    }

    fun findById(id: Long): Topic {
        return topicList.first {
            it.id == id
        }
    }

}