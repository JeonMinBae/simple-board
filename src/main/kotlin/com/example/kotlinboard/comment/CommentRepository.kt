package com.example.kotlinboard.comment

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByBoardId(boardId: Long): List<Comment>
    fun deleteByBoardId(boardId: Long)
}