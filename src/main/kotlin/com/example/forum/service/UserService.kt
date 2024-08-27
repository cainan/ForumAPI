package com.example.forum.service

import com.example.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private var userList: ArrayList<User> = ArrayList()) {
    init {
        val user1 = User(id = 1, name = "User I", email = "user1@gmail.com")
        userList.add(user1)
    }

    fun findById(id: Long): User {
        return userList.first {
            it.id == id
        }
    }
}

