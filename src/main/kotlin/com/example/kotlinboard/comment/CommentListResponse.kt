package com.example.kotlinboard.comment

import java.time.LocalDateTime

class CommentListResponse(
    val id: Long,
    val content: String,
    val boardId: Long,
    val author: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    constructor(comment: Comment) : this(
        id = comment.id!!,
        content = comment.content,
        boardId = comment.boardId,
        author = comment.registerUser,
        createdAt = comment.createdAt!!,
        updatedAt = comment.updatedAt!!
    )
}