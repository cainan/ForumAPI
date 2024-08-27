package com.example.forum.model

data class Course(
    val id: Long? = null,
    val name: String,
    val category: String
) {
    override fun toString(): String {
        return "Course(id=$id, name='$name', category='$category')"
    }
}