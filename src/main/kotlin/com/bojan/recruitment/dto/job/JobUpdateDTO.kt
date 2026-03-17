package com.bojan.recruitment.dto.job

data class JobUpdateDTO(
    val title: String,
    val description: String,
    val requirements: String,
    val location: String,
    val experienceLevel: String,
    val salaryRange: String
)
