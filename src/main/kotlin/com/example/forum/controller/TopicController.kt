package com.example.forum.controller

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicPerCategoryDto
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.service.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) courseName: String?,
        /*@PageableDefault(size = 5)*/ pagination: Pageable,
    ): Page<TopicView> {
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun add(
        @RequestBody @Valid newTopicForm: NewTopicForm,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<TopicView> {
        val topicView = service.add(newTopicForm)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    fun update(
        @RequestBody @Valid updateTopicForm: UpdateTopicForm,
    ): ResponseEntity<TopicView> {
        val topicView = service.update(updateTopicForm)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun remove(@PathVariable id: Long) {
        service.remove(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicPerCategoryDto> {
        return service.createReport()
    }
}