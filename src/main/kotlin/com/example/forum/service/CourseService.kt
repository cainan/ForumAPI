package com.example.forum.service

import com.example.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseList: ArrayList<Course> = ArrayList()) {
    init {
        val course1 = Course(id = 1L, name = "Kotlin I", category = "Software")
        courseList.add(course1)
    }

    fun findById(id: Long): Course {
        return courseList.first {
            it.id == id
        }
    }
}
