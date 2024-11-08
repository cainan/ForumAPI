package com.example.forum.service

import com.example.forum.dto.NewTopicForm
import com.example.forum.dto.TopicPerCategoryDto
import com.example.forum.dto.TopicView
import com.example.forum.dto.UpdateTopicForm
import com.example.forum.exception.NotFoundException
import com.example.forum.mapper.TopicFormMapper
import com.example.forum.mapper.TopicViewMapper
import com.example.forum.repository.TopicRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    /*private val em: EntityManager*/
) {

    @Cacheable("topicListCache", key = "#root.method.name")
    fun list(courseName: String?, pagination: Pageable): Page<TopicView> {
//        println(em)
        val topicList = if (courseName == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }
        return topicList.map { topic ->
            topicViewMapper.map(topic)
        }
    }

    fun findById(id: Long): TopicView {
        return topicViewMapper.map(
            repository.findById(id).orElseThrow {
                NotFoundException("Topic not found")
            }
        )
    }

    @CacheEvict(value = ["topicListCache"], allEntries = true)
    fun add(newTopicForm: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(newTopicForm)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    @CacheEvict(value = ["topicListCache"], allEntries = true)
    fun update(updateTopicForm: UpdateTopicForm): TopicView {
        val topic = repository.findById(updateTopicForm.id).orElseThrow {
            NotFoundException("Topic not found")
        }
        topic.apply {
            title = updateTopicForm.title
            message = updateTopicForm.message
            editedDate = LocalDate.now()
        }
        return topicViewMapper.map(topic)
    }

    @CacheEvict(value = ["topicListCache"], allEntries = true)
    fun remove(id: Long) {
//        if (!repository.existsById(id)) {
//            throw NotFoundException("Topic not found")
//        }
        repository.deleteById(id)
    }

    fun createReport(): List<TopicPerCategoryDto> {
        return repository.createReport()
    }

}