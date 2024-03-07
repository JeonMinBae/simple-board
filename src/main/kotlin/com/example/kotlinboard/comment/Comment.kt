package com.example.kotlinboard.comment

import com.example.kotlinboard.common.BaseEntity
import jakarta.persistence.*

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