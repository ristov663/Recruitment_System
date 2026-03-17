package com.bojan.recruitment.dto.application

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class ApplicationRequestDTO(
    @field:NotNull
    val jobId: UUID,
    val cvUrl: String?
)
