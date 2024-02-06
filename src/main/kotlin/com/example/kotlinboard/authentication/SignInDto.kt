package com.example.kotlinboard.authentication

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class SignInDto(
    @NotBlank
    val id: String,
    // 소문자, 숫자, 특수문자(!@#$%^&*()-_=+)를 각각 1개 이상 포함한 8 ~ 16자리
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z0-9])(?=.*[!@#$%^&*_-]).{8,16}$")
    val password: String
) {
}