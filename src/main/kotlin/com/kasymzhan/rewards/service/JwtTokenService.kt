package com.kasymzhan.rewards.service

import com.kasymzhan.rewards.config.JwtConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service

@Service
class JwtTokenService(jwtConfig: JwtConfig) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtConfig.secret.toByteArray()
    )

    fun isValid(token: String?): Boolean {
        if (token.isNullOrBlank())
            return false
        try {
            getAllClaims(token)
            return true
        } catch (e: Exception) {
            println("token parse returns false: $e")
            return false
        }
    }

    fun getAllClaims(token: String): Claims {
        val decoder = Jwts.parser().verifyWith(secretKey).build()
        return decoder.parseSignedClaims(token).payload
    }
}