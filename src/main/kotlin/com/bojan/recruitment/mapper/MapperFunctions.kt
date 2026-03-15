package com.bojan.recruitment.mapper

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.user.UserRequestDTO
import com.bojan.recruitment.dto.user.UserResponseDTO
import com.bojan.recruitment.model.User
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

fun UserRequestDTO.toEntity() = User(
    id = UUID.randomUUID(),
    firstName = firstName,
    lastName = lastName,
    userName = userName,
    email = email,
    passwordValue = encoder().encode(password).toString(),
    role = role
)

fun User.toDto() = UserResponseDTO(
    id = id,
    firstName = firstName,
    lastName = lastName,
    userName = username,
    email = email,
    role = role
)

fun <T> Page<T>.toPageDto(): PageResponse<T> {
    return PageResponse(
        content = this.content,
        page = this.number,
        size = this.size,
        totalElements = this.totalElements,
        totalPages = this.totalPages,
        last = this.isLast
    )
}

fun encoder(): PasswordEncoder = BCryptPasswordEncoder()
