package com.example.forum.service

import com.example.forum.model.Course
import com.example.forum.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun findById(id: Long): Course {
        return repository.getReferenceById(id)
    }
}
