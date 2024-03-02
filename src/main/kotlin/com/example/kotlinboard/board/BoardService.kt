package com.example.kotlinboard.board

import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    val boardRepository: BoardRepository,
    val eventPublisher: ApplicationEventPublisher
) {

    @Transactional(readOnly = true)
    fun getBoards(pageable: Pageable): Page<BoardListResponse> {
        return boardRepository.findAll(pageable).map { BoardListResponse(it)}
    }

    @Transactional
    fun getBoard(id: Long): BoardDetailResponse {
        val findBoard = findBoard(id)
        findBoard.countUpView()

        return BoardDetailResponse(findBoard)
    }

    @Transactional
    fun createBoard(request: CreateBoardRequest): Long {
        val board = Board(title = request.title, content = request.content)

        val newBoard = boardRepository.save(board)
        return newBoard.id!!
    }

    @Transactional
    fun updateBoard(id: Long, request: UpdateBoardRequest): Long {
        val findBoard = findBoard(id)
        findBoard.update(request)

        return findBoard.id!!
    }

    @Transactional
    fun deleteBoard(id: Long) {
        boardRepository.deleteById(id)
        eventPublisher.publishEvent(BoardDeletedEvent(id))
    }

    private fun findBoard(id: Long): Board =
        boardRepository.findById(id).orElseThrow { throw IllegalStateException("게시글을 찾을 수 없습니다.") }

}