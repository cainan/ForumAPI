package com.example.forum.service

import com.example.forum.model.UserT
import com.example.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun findById(id: Long): UserT {
        return repository.getReferenceById(id)
    }
}