package com.bojan.recruitment.dto.application

import com.bojan.recruitment.enums.ApplicationStatus
import java.time.LocalDateTime
import java.util.*

data class ApplicationResponseDTO(
    val id: UUID,
    val candidate: String,
    val jobTitle: String,
    val status: ApplicationStatus,
    val appliedAt: LocalDateTime,
    val cvUrl: String?
)
