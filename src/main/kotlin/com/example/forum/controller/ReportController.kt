package com.example.forum.controller

import com.example.forum.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/reports")
class ReportController(
    private val topicService: TopicService,
) {

    @GetMapping
    fun report(model: Model): String {
        model.addAttribute("topicsByCategories", topicService.createReport())
        return "report"
    }
}