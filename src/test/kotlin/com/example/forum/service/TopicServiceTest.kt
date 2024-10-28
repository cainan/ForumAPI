package com.example.forum.service

import com.example.forum.exception.NotFoundException
import com.example.forum.mapper.TopicFormMapper
import com.example.forum.mapper.TopicViewMapper
import com.example.forum.model.Topic
import com.example.forum.model.TopicTest
import com.example.forum.model.TopicViewTest
import com.example.forum.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {

    val topics = PageImpl(listOf(TopicTest.build()))
    val pageable: Pageable = mockk()

    val topicRepository: TopicRepository = mockk {

        every {
            findByCourseName(any(), any())
        } returns topics

        every {
            findAll(pageable)
        } returns topics
    }
    val topicViewMapper: TopicViewMapper = mockk {
        every { map(any()) } returns TopicViewTest.build()
    }
    val topicFormMapper: TopicFormMapper = mockk()

    val topicService = TopicService(topicRepository, topicViewMapper, topicFormMapper)

    val slot = slot<Topic>()

    @Test
    fun `must list topics from course name`() {
        every { topicViewMapper.map(capture(slot)) } returns TopicViewTest.build()

        topicService.list("Curso Kotlin Basico", pageable)
        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pageable) }

        val topic = TopicTest.build()
        println(slot.captured)
        assertThat(slot.captured.title).isEqualTo(topic.title)
        assertThat(slot.captured.message).isEqualTo(topic.message)
        assertThat(slot.captured.status).isEqualTo(topic.status)
    }

    @Test
    fun `must list all topics when course name is null`() {
        topicService.list(null, pageable)
        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 1) { topicRepository.findAll(pageable) }
    }

    @Test
    fun `Not found exception when topic is not found`() {
        every { topicRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> { topicService.findById(1) }

        assertThat(actual.message).isEqualTo("Topic not found")
    }
}