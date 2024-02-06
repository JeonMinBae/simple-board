package com.example.kotlinboard.authentication

import com.example.kotlinboard.user.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AuthenticationServiceTest{
    private val authenticationService = AuthenticationService(UserRepository())


    @Test
    fun signIn() {
        val token = authenticationService.signIn(SignInDto("user1@cawave.co.kr", "qwe123!@#"))

        authenticationService.authenticate(token)
    }


}