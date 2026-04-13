package com.bojan.recruitment.service.impl

import com.bojan.recruitment.config.JwtProperties
import com.bojan.recruitment.repository.UserRepository
import com.bojan.recruitment.service.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class TokenServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository
) : TokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray(),
    )
    private val invalidatedTokens = ConcurrentHashMap.newKeySet<String>()

    override fun generateAccessToken(userDetails: UserDetails): String {
        val expiration = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        return generateToken(userDetails, expiration)
    }

    override fun generateRefreshToken(userDetails: UserDetails): String {
        val expiration = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
        return generateToken(userDetails, expiration)
    }

    private fun generateToken(userDetails: UserDetails, expirationDate: Date): String {
        val user = userRepository.findByUserName(userDetails.username)

        return Jwts.builder()
            .subject(user.email.trim().lowercase())
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .claim("userId", user.id.toString())
            .signWith(secretKey)
            .compact()
    }

    override fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    override fun isValid(token: String): Boolean {
        return try {
            val parser = Jwts.parser()
                .verifyWith(secretKey)
                .build()

            val claims = parser.parseSignedClaims(token)
            !claims.payload.expiration.before(Date())
        } catch (ex: Exception) {
            false
        }
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    override fun invalidateToken(token: String) {
        invalidatedTokens.add(token)
    }
}
