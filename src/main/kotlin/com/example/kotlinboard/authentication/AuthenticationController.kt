package com.example.kotlinboard.authentication

import com.example.kotlinboard.common.Response
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    val authenticationService: AuthenticationService
) {

    @PostMapping("/sign-in")
    fun signIn(@Valid @RequestBody signInDto: SignInDto): ResponseEntity<Any> {
        val token = authenticationService.signIn(signInDto)

        return Response.ok(token)
    }

}