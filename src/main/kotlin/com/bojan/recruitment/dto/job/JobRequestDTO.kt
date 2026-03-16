package com.bojan.recruitment.dto.job

import jakarta.validation.constraints.NotBlank

data class JobRequestDTO(

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val description: String,

    @field:NotBlank
    val requirements: String,

    @field:NotBlank
    val location: String,

    @field:NotBlank
    val experienceLevel: String,

    @field:NotBlank
    val salaryRange: String

)
