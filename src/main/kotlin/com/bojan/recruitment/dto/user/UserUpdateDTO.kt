package com.bojan.recruitment.dto.user

import jakarta.validation.constraints.NotBlank

data class UserUpdateDTO(
    @field:NotBlank(message = "First name cannot be blank")
    val firstName: String,
    @field:NotBlank(message = "Last name cannot be blank")
    val lastName: String,
    @field:NotBlank(message = "Username cannot be blank")
    val userName: String
)

