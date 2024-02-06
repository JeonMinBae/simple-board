package com.example.kotlinboard.board

import com.example.kotlinboard.common.Response
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class BoardController(
    val boardService: BoardService
) {

    @GetMapping("")
    fun getBoards(pageable: Pageable): ResponseEntity<Any> {
        val boardPage = boardService.getBoards(pageable)
        return Response.ok(boardPage)
    }

    @GetMapping("/{id}")
    fun getBoards(@PathVariable id: Long): ResponseEntity<Any> {
        val boardDetail = boardService.getBoard(id)
        return Response.ok(boardDetail)
    }

    @PostMapping("")
    fun createBoard(@RequestBody request: CreateBoardRequest): ResponseEntity<Any> {
        val board = boardService.createBoard(request)
        return Response.ok(board)
    }

    @PatchMapping("/{id}")
    fun updateBoard(@PathVariable id: Long, @RequestBody request: UpdateBoardRequest): ResponseEntity<Any> {
        val updateBoardId = boardService.updateBoard(id, request)
        return Response.ok(updateBoardId)
    }

    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Long): ResponseEntity<Any> {
        boardService.deleteBoard(id)
        return Response.noContent()
    }

}