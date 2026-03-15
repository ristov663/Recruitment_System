package com.bojan.recruitment.service.impl

import com.bojan.recruitment.controller.auth.AuthenticationRequest
import com.bojan.recruitment.controller.auth.AuthenticationResponse
import com.bojan.recruitment.service.AuthenticationService
import com.bojan.recruitment.service.TokenService
import com.bojan.recruitment.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userService: UserService,
    private val tokenService: TokenService
) : AuthenticationService {

    override fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        )

        val user = userService.loadUserByUsername(authRequest.email)

        val accessToken = tokenService.generateAccessToken(user)
        val refreshToken = tokenService.generateRefreshToken(user)

        return AuthenticationResponse(accessToken, refreshToken)
    }

    override fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)

        return extractedEmail?.let { email ->
            val currentUserDetails = userService.loadUserByUsername(email)

            if (tokenService.isValid(token))
                tokenService.generateAccessToken(currentUserDetails)
            else null
        }
    }

    override fun logout(token: String) {
        tokenService.invalidateToken(token)
    }
}
