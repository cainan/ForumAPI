package com.example.forum.integration

import com.example.forum.dto.TopicPerCategoryDto
import com.example.forum.model.TopicTest
import com.example.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private lateinit var topicRepository: TopicRepository
    private val topic = TopicTest.build()

    //    Topic(id=1, title=Duvida sobre kotlin, message=Minha funcao let nao funciona, creationDate=2021-12-24T12:00, course=Course(id=1, name=Kotlin, category=Software), author=User(id=1, name=Nann, email=np@gmail.com, password=$2a$12$x9P3fTidJRZ92Jzwt8g2MO.9CcvKORf932fa07SxqM8/1.8LN4iwS, role=[Role(id=1, name=READ_WRITE)]), status=NOT_ANSWERED, answers=[], editedDate=null)
    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.32").apply {
            withDatabaseName("testdb")
            withUsername("nan")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
            registry.add("spring.datasource.password", mysqlContainer::getPassword);
            registry.add("spring.datasource.username", mysqlContainer::getUsername);
        }
    }

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