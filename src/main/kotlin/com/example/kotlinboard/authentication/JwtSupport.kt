package com.example.kotlinboard.authentication

import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets

object JwtSupport {
    private const val SECRET_KEY = "h3ut02yt!h12(1%gui4ug@789t07928oh&#%by9yn98"
    private val hmacShaKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))

    fun getSecretKey() = hmacShaKey

    object Claim{
        const val USER_ID = "userId"
        const val NAME = "name"
    }

}