package com.bojan.recruitment.dto.email

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.util.UUID

data class SendEmailRequestDTO(

    @field:NotEmpty
    val applicationIds: List<UUID>,

    @field:NotBlank
    val subject: String,

    @field:NotBlank
    val body: String
)
