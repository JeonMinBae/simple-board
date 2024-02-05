package com.example.kotlinboard.board

import java.time.LocalDateTime

class BoardListResponse(
    val id: Long,
    val title: String,
    val view: Long,
    val createdAt: LocalDateTime
) {
    constructor(board: Board) : this(
        id = board.id!!,
        title = board.title,
        view = board.view,
        createdAt = board.createdAt
    )
}