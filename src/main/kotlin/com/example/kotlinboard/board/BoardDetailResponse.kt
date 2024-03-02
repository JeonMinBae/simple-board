package com.example.kotlinboard.board

import java.time.LocalDateTime

class BoardDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val view: Long,
    val author: String,
    val createdAt: LocalDateTime?
) {
    constructor(board: Board) : this(
        id = board.id!!,
        title = board.title,
        content = board.content,
        view = board.view,
        author = board.registerUser,
        createdAt = board.createdAt
    )
}