package com.example.kotlinboard.board

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class BoardServiceTest(
) {
    lateinit var boardService: BoardService
    lateinit var boardRepository: BoardRepository
    lateinit var eventPublisher: ApplicationEventPublisher

    @BeforeEach
    fun setUp() {
        boardRepository = Mockito.mock(BoardRepository::class.java)
        eventPublisher = Mockito.mock(ApplicationEventPublisher::class.java)
        boardService = BoardService(boardRepository, eventPublisher)
    }

    @Test
    fun test() {
        val board = Board(id = 1L, title = "title", content = "content", author = "author")
        `when`(boardRepository.findById(1L)).thenReturn(Optional.of(board))
        val getBoard = boardService.getBoard(1L)

        Assertions.assertThat(getBoard.view).isEqualTo(1)
    }

}