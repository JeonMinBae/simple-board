package com.example.kotlinboard.comment

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long? = null,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val boardId: Long
) {

    fun update(update: UpdateCommentRequest){
        content = update.content
    }
}