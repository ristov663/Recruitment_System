package com.bojan.recruitment.dto.job

import java.time.LocalDateTime
import java.util.*

data class JobResponseDTO(

    val id: UUID,
    val title: String,
    val description: String,
    val requirements: String,
    val location: String,
    val experienceLevel: String,
    val salaryRange: String,
    val createdAt: LocalDateTime,
    val createdBy: String

)
