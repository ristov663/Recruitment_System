package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.email.SendEmailRequestDTO
import com.bojan.recruitment.service.EmailService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/emails")
class EmailController(
    private val emailService: EmailService
) {

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.OK)
    fun sendBulkEmails(
        @RequestBody @Valid request: SendEmailRequestDTO
    ) {
        emailService.sendBulkEmail(
            request.applicationIds,
            request.subject,
            request.body
        )
    }
}
