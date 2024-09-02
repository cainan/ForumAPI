package com.example.forum.controller

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

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
    fun add(
        @RequestBody @Valid newTopicForm: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.add(newTopicForm)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    fun update(
        @RequestBody @Valid updateTopicForm: UpdateTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.update(updateTopicForm)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable id: Long) {
        service.remove(id)
    }
}