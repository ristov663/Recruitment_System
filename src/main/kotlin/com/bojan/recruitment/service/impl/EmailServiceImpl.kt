package com.bojan.recruitment.service.impl

import com.bojan.recruitment.repository.ApplicationRepository
import com.bojan.recruitment.service.EmailService
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender,
    private val applicationRepository: ApplicationRepository
) : EmailService {

    override fun sendEmail(to: String, subject: String, body: String) {
        val message = SimpleMailMessage()
        message.setTo(to)
        message.subject = subject
        message.text = body

        mailSender.send(message)
    }

    override fun sendBulkEmail(applicationIds: List<UUID>, subject: String, body: String) {

        val applications = applicationRepository.findAllById(applicationIds)

        applications.forEach {
            val email = it.candidate.email
            sendEmail(email, subject, body)
        }
    }

    override fun sendInterviewScheduledEmail(email: String, date: String, notes: String) {

        val subject = "Interview Invitation"

        val body = """
            You have been invited to an interview.
            
            Date: $date
            
            Notes:
            $notes
            
            Best regards,
            HR Team
        """.trimIndent()

        sendEmail(email, subject, body)
    }
}
