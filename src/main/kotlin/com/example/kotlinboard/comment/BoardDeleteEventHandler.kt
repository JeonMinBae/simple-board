package com.example.kotlinboard.comment

import com.example.kotlinboard.board.BoardDeletedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT
import org.springframework.transaction.event.TransactionalEventListener

@Component
class BoardDeleteEventHandler(
    val commentService: CommentService
) {

    @TransactionalEventListener(classes = [BoardDeletedEvent::class], phase = BEFORE_COMMIT)
    fun handle(event: BoardDeletedEvent) {
        commentService.deleteByBoardId(event.boardId)
    }
}