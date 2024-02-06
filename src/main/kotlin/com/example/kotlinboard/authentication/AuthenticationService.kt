package com.example.kotlinboard.authentication

import com.example.kotlinboard.user.User
import com.example.kotlinboard.user.UserRepository
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class AuthenticationService(
    val userRepository: UserRepository
) {
    fun signIn(signInDto: SignInDto): String {
        val user =
            userRepository.findById(signInDto.id).orElseThrow { IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.") }
        if (user.password != signInDto.password) {
            throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        }

        return createToken(user)
    }

    private fun createToken(user: User): String {
        val issueDate = Date()
        val expireSecond = 30 * 60 * 1000
        return Jwts.builder()
            .issuedAt(issueDate)
            .expiration(Date(issueDate.time + expireSecond))
            .claim(JwtSupport.Claim.USER_ID, user.userId)
            .claim(JwtSupport.Claim.NAME, user.name)
            .signWith(JwtSupport.getSecretKey())
            .compact()
    }

    fun authenticate(token: String) {
        val claims = getTokenClaims(token, JwtSupport.Claim.USER_ID)
        val findUser = userRepository.findById(claims).orElseThrow { IllegalArgumentException("세션이 만료되었습니다.") }

        CurrentUser.set(findUser)
    }

    private fun getTokenClaims(token: String, claim: String): String {
        return Jwts.parser()
            .verifyWith(JwtSupport.getSecretKey())
            .build()
            .parseSignedClaims(token)
            .payload
            .get(claim, String::class.java)

    }
}