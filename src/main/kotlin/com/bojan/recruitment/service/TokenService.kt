package com.bojan.recruitment.service

import org.springframework.security.core.userdetails.UserDetails

interface TokenService {

    fun generateAccessToken(userDetails: UserDetails): String
    fun generateRefreshToken(userDetails: UserDetails): String
    fun extractEmail(token: String): String?
    fun isValid(token: String): Boolean
    fun invalidateToken(token: String)
}
