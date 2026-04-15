package com.bojan.recruitment.service

import java.util.UUID

interface EmailService {

    fun sendEmail(to: String, subject: String, body: String)
    fun sendBulkEmail(applicationIds: List<UUID>, subject: String, body: String)
    fun sendInterviewScheduledEmail(email: String, date: String, notes: String)
}
