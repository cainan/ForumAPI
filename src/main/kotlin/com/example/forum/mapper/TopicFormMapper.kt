package com.example.forum.mapper

import com.example.forum.dto.NewTopicForm
import com.example.forum.model.Topic
import com.example.forum.service.CourseService
import com.example.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
) : Mapper<NewTopicForm, Topic> {

    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.idCourse),
            author = userService.findById(t.idAuthor),
        )
    }
}