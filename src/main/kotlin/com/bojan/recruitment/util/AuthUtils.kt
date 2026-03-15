package com.bojan.recruitment.util

import com.bojan.recruitment.model.User
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

fun getCurrentUserId(): UUID {
    val authentication = SecurityContextHolder.getContext().authentication
        ?: throw IllegalStateException("No authentication found")

    val user = authentication.principal as? User
        ?: throw IllegalStateException("Principal is not a valid UserEntity")

    return user.id
}
