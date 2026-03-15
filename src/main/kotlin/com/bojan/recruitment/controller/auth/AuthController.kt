package com.bojan.recruitment.controller.auth

import com.bojan.recruitment.config.extractBearerToken
import com.bojan.recruitment.service.AuthenticationService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    fun refreshAccessToken(request: HttpServletRequest): TokenResponse {
        val refreshToken = request.extractBearerToken()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing refresh token in Authorization header")

        return authenticationService.refreshAccessToken(refreshToken)
            ?.let { TokenResponse(token = it) }
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token!")
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    fun logout(@RequestHeader("Authorization") token: String?) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token")
        }

        val jwt = token.removePrefix("Bearer ").trim()
        authenticationService.logout(jwt)
    }
}
