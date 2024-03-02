package com.example.kotlinboard.comment

import com.example.kotlinboard.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Comment(
    var content: String,
    val boardId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long? = null,
):BaseEntity() {

    fun update(update: UpdateCommentRequest){
        content = update.content
    }
}