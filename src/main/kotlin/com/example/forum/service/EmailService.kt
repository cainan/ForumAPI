package com.example.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
) {

    fun notify(to: String) {
        val message = SimpleMailMessage()
        message.apply {
            subject = "Your topic was replied"
            text = "Your topic was replied, lets check it?"
            setTo(to)
        }
        javaMailSender.send(message)
    }
}