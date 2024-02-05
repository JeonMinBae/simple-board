package com.example.kotlinboard.common

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.util.*

class Response<T>(
    val requestId: String = UUID.randomUUID().toString(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Status,
    val data: T
) {
    companion object {
        fun <S> ok(data: S): ResponseEntity<Any> = ResponseEntity.ok(Response(status = Status.SUCCESS, data = data))
        fun noContent(): ResponseEntity<Any> = ResponseEntity.noContent().build()
        fun error(status: HttpStatusCode, message: String): ResponseEntity<Any> =
            ResponseEntity.status(status).body(Response(status = Status.ERROR, data = message))
    }

    enum class Status {
        SUCCESS,
        ERROR,
    }
}