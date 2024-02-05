package com.example.kotlinboard.board

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    val id: Long? = null,
    var title: String,
    var content: String,
    var view: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now()
){

    fun update(update: UpdateBoardRequest){
        title = update.title
        content = update.content
    }

    fun countUpView(){
        view++
    }

}