package com.bojan.recruitment.dto.interview

import java.time.LocalDateTime
import java.util.*

data class InterviewResponseDTO(
    val id: UUID,
    val applicationId: UUID,
    val scheduledAt: LocalDateTime,
    val interviewer: String,
    val notes: String?,
    val score: Double?
)
