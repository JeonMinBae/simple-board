package com.example.kotlinboard.board

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

class BoardServiceTest(
) {
    lateinit var boardService: BoardService
    lateinit var boardRepository: BoardRepository

    @BeforeEach
    fun setUp() {
        boardRepository = Mockito.mock(BoardRepository::class.java)
        boardService = BoardService()
        boardService.boardRepository = boardRepository
    }

    @Test
    fun test() {
        val board = Board(id = 1L, title = "title", content = "content")
        `when`(boardRepository.findById(1L)).thenReturn(Optional.of(board))
        val getBoard = boardService.getBoard(1L)

        Assertions.assertThat(getBoard.view).isEqualTo(1)
    }

}