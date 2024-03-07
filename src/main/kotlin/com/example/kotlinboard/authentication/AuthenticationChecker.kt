package com.example.kotlinboard.authentication

import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


private const val AUTHORIZATION = "Authorization"
private const val BEARER_PREFIX = "Bearer "

@Aspect
@Component
class AuthenticationChecker(
    val authenticationService: AuthenticationService
) {

    @Before("execution(* com.example.kotlinboard..*Controller.*(..)) && !within(com.example.kotlinboard..AuthenticationController)")
    fun authenticationPointcut() {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val bearerToken = request.getHeader(AUTHORIZATION)

        try {
            val jwt = bearerToken.slice(BEARER_PREFIX.length until bearerToken.length)
            authenticationService.authenticate(jwt)
        } catch (e: Exception) {
            throw IllegalArgumentException("인증되지 않은 사용자입니다.")
        }

    }

    @After("execution(* com.example.kotlinboard..*Controller.*(..)) ")
    fun removeCurrentUser() {
        CurrentUser.remove()
    }


}
