package com.example.forum.controller

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.service.TopicService
import jakarta.validation.Valid
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
    fun add(@RequestBody @Valid newTopicForm: NewTopicForm) {
        service.add(newTopicForm)
    }

    @PutMapping
    fun update(@RequestBody @Valid updateTopicForm: UpdateTopicForm) {
        service.update(updateTopicForm)
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long) {
        service.remove(id)
    }
}