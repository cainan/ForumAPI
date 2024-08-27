package com.example.forum.controller

import com.example.forum.dto.TopicForm
import com.example.forum.dto.TopicView
import com.example.forum.service.TopicService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<TopicView> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView {
        return service.findById(id)
    }

    @PostMapping
    fun add(@RequestBody topicForm: TopicForm) {
        service.add(topicForm)
    }
}