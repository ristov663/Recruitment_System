package com.bojan.recruitment.controller.auth

import jakarta.validation.constraints.Email

data class AuthenticationRequest(
    @field:Email(message = "Must be a valid email address")
    val email: String,
    val password: String
)
