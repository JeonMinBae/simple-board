package com.example.kotlinboard.comment

import com.example.kotlinboard.authentication.CurrentUser
import com.example.kotlinboard.board.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    val commentRepository: CommentRepository,
    val boardRepository: BoardRepository
) {

    @Transactional(readOnly = true)
    fun getComments(boardId: Long): List<Comment> {
        checkExistBoard(boardId)

        return commentRepository.findAllByBoardId(boardId)
    }

    @Transactional
    fun createComment(request: CreateCommentRequest): Long {
        checkExistBoard(request.boardId)
        val comment = Comment(content = request.content, boardId = request.boardId, author = CurrentUser.username)
        val newComment = commentRepository.save(comment)

        return newComment.id!!
    }

    private fun checkExistBoard(boardId: Long) {
        if (!boardRepository.existsById(boardId)) {
            throw IllegalStateException("게시글을 찾을 수 없습니다.")
        }
    }

    @Transactional
    fun updateComment(id: Long, request: UpdateCommentRequest): Long {
        val findComment = findComment(id)
        findComment.update(request)

        return findComment.id!!
    }

    private fun findComment(id: Long): Comment =
        commentRepository.findById(id).orElseThrow { throw IllegalStateException("댓글을 찾을 수 없습니다.") }

    @Transactional
    fun delete(id: Long) {
        commentRepository.deleteById(id)
    }

    @Transactional
    fun deleteByBoardId(boardId: Long) {
        commentRepository.deleteByBoardId(boardId)
    }


}