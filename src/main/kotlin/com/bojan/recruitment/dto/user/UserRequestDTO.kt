package com.bojan.recruitment.dto.user

import com.bojan.recruitment.enums.UserRole
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserRequestDTO(

    @field:NotBlank(message = "First name cannot be blank")
    @field:Size(min = 5, max = 15, message = "First name must be between 5 and 15 characters")
    val firstName: String,

    @field:NotBlank(message = "Last name cannot be blank")
    @field:Size(min = 5, max = 15, message = "Last name must be between 5 and 15 characters")
    val lastName: String,

    @field:Pattern(
        regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$",
        message = "Username must start with a letter and contain 8 to 30 characters. Only letters, numbers, and underscores are allowed."
    )
    val userName: String,

    @field:Email(message = "Must be a valid email address")
    val email: String,

    @field:Pattern(
        regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}$",
        message = "Password should have minimum 8 characters with uppercase, lowercase, digit and special character."
    )
    val password: String,

    val role: UserRole
)
