package com.example.forum.controller

import com.example.forum.dto.TopicPerCategoryDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reports")
class ReportController(
    private val topicController: TopicController,
) {

    @GetMapping
    fun report(): List<TopicPerCategoryDto> {
        return topicController.report()
    }
}