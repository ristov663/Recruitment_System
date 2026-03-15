package com.bojan.recruitment.dto.user

import com.bojan.recruitment.enums.UserRole
import java.util.UUID

data class UserResponseDTO(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val email: String,
    val role: UserRole
)
