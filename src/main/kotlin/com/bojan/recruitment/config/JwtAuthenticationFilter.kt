package com.bojan.recruitment.config

import com.bojan.recruitment.service.TokenService
import com.bojan.recruitment.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

fun HttpServletRequest.extractBearerToken(): String? {
    return getHeader("Authorization")
        ?.takeIf { it.startsWith("Bearer ", ignoreCase = true) }
        ?.substring(7)
        ?.takeIf { it.isNotBlank() }
}

@Component
class JwtAuthenticationFilter(
    private val userService: UserService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtToken = request.extractBearerToken()

        if (jwtToken == null) {
            filterChain.doFilter(request, response)
            return
        }

        val email = tokenService.extractEmail(jwtToken)
            ?: throw IllegalStateException("Token does not contain a valid email")

        val user = userService.loadUserByUsername(email)

        if (tokenService.isValid(jwtToken)) {
            updateContext(user, request)
        }

        filterChain.doFilter(request, response)
    }

    private fun updateContext(userDetails: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }
}
