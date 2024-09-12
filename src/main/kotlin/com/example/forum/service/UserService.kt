package com.example.forum.service

import com.example.forum.model.User
import com.example.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun findById(id: Long): User {
        return repository.getReferenceById(id)
    }
}