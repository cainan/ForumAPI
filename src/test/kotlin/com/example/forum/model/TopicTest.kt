package com.example.forum.model

object TopicTest {
    fun build() = Topic (
        id = 1,
        title = "Kotlin Basics",
        message = "Duvida na funcao",
        course = CourseTest.build(),
        author = UserTest.build()
    )
}