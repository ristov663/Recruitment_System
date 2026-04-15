package com.bojan.recruitment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mail.javamail.JavaMailSender

@SpringBootTest
class RecruitmentSystemApplicationTests {

    @MockBean
    lateinit var javaMailSender: JavaMailSender

    @Test
    fun contextLoads() {
    }

}
