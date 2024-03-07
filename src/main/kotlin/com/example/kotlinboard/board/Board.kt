package com.example.kotlinboard.board

import com.example.kotlinboard.common.BaseEntity
import jakarta.persistence.*

@Entity
class Board (
    var title: String,
    var content: String,
    var view: Long = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    val id: Long? = null,
): BaseEntity(){

    fun update(update: UpdateBoardRequest){
        title = update.title
        content = update.content
    }

    fun countUpView(){
        view++
    }

}