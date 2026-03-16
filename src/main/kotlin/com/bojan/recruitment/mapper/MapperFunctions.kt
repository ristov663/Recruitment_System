package com.bojan.recruitment.mapper

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.job.JobRequestDTO
import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.dto.user.UserRequestDTO
import com.bojan.recruitment.dto.user.UserResponseDTO
import com.bojan.recruitment.model.Job
import com.bojan.recruitment.model.User
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.AbstractAuditable_.createdBy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*
import java.util.UUID

fun UserRequestDTO.toUserEntity() = User(
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

fun JobRequestDTO.toJobEntity() = Job(
    id = UUID.randomUUID(),
    title = title,
    description = description,
    requirements = requirements,
    location = location,
    experienceLevel = experienceLevel,
    salaryRange = salaryRange,
    createdBy = createdBy as User,
)

fun Job.toDto() = JobResponseDTO(
    id = id,
    title = title,
    description = description,
    requirements = requirements,
    location = location,
    experienceLevel = experienceLevel,
    salaryRange = salaryRange,
    createdAt = createdAt,
    createdBy = createdBy.userName
)

fun <T : Any> Page<T>.toPageDto(): PageResponse<T> {
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
