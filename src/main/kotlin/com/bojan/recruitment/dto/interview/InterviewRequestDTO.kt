package com.bojan.recruitment.dto.interview

import java.time.LocalDateTime
import java.util.*

data class InterviewRequestDTO(
    val applicationId: UUID,
    val scheduledAt: LocalDateTime,
    val interviewer: String
)
