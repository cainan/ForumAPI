package com.example.forum.service

import com.example.forum.dto.TopicForm
import com.example.forum.dto.TopicView
import com.example.forum.model.Topic
import com.example.forum.model.toTopicView
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topicList: ArrayList<Topic> = ArrayList(),
    private val courseService: CourseService,
    private val userService: UserService
) {

//    init {
//        val topic1 = Topic(
//            id = 1,
//            title = "Duvida sobre Spring",
//            message = "Estou com duvida nessa parte",
//            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
//            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
//        )
//        val topic2 = Topic(
//            id = 2,
//            title = "Duvida sobre Kotlin",
//            message = "Estou com duvida nessa parte",
//            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
//            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
//        )
//        val topic3 = Topic(
//            id = 3,
//            title = "Duvida sobre Android",
//            message = "Estou com duvida nessa parte",
//            course = Course(id = 1, name = "Curso de Spring", category = "Dev"),
//            author = User(id = 1, name = "Nan", email = "cs@gm.cm"),
//        )
//
//        topicList = arrayListOf(topic1, topic2, topic3)
//    }

    fun list(): List<TopicView> {
        return topicList.map { topic ->
            topic.toTopicView()
        }
    }

    fun findById(id: Long): TopicView {
        return topicList.first {
            it.id == id
        }.toTopicView()
    }

    fun add(topicForm: TopicForm) {
        topicList.add(
            Topic(
                id = topicList.size + 1L,
                title = topicForm.title,
                message = topicForm.message,
                course = courseService.findById(topicForm.idCourse),
                author = userService.findById(topicForm.idAuthor),
            )
        )
    }

}