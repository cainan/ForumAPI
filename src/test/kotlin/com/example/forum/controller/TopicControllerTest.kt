package com.example.forum.controller

import com.example.forum.config.JWTUtil
import com.example.forum.configuration.DatabaseContainerConfiguration
import com.example.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val TOKEN = "%s"
        private const val RESOURCE = "/topics"
        private const val RESOURCE_ID = RESOURCE.plus("/%s")
    }


    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()

        token = generateToken()
    }

    @Test
    fun `must return code 400 when calling without token`() {
        mockMvc.get(RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `must return code 200 when calling with token`() {
        println("----------> $token")

        mockMvc.get(RESOURCE) {
            headers { this.setBearerAuth(TOKEN.format(token)) }
        }.andExpect { status { isOk() } }

//        mockMvc.get(RESOURCE) {
//            headers {
//                token?.let { setBearerAuth(it) }
//            }
//        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `must return code 200 when calling with id with token`() {
        mockMvc.get(RESOURCE_ID.format("1")) {
            headers {
                token?.let { setBearerAuth(it) }
            }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf(Role(1, "READ_WRITE"))
        return jwtUtil.generateToken("np@gmail.com", authorities)
    }
}