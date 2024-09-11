package com.example.forum.repository

import com.example.forum.model.UserT
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserT, Long> {
}