package com.example.kotlinboard.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateException(e: IllegalStateException) =
        Response.error(HttpStatus.NOT_FOUND, e.message?: "에러가 발생하였습니다.")

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException) =
        Response.error(HttpStatus.UNAUTHORIZED, e.message?: "에러가 발생하였습니다.")



}