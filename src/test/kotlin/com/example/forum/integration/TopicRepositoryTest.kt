package com.example.forum.integration

import com.example.forum.configuration.DatabaseContainerConfiguration
import com.example.forum.dto.TopicPerCategoryDto
import com.example.forum.model.TopicTest
import com.example.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var topicRepository: TopicRepository
    private val topic = TopicTest.build()

    @Test
    fun `must generate a report`() {
        topicRepository.save(topic)
        val report = topicRepository.createReport()

        assertThat(report).isNotNull
        assertThat(report.first()).isExactlyInstanceOf(TopicPerCategoryDto::class.java)
    }

    @Test
    fun `must list topic per course name`() {
        topicRepository.save(topic)
        val topicByCourseName = topicRepository.findByCourseName(
            courseName = topic.course.name,
            pagination = PageRequest.of(0, 5),
        )
        assertThat(topicByCourseName).isNotNull
    }


}