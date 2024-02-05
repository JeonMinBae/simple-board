package com.example.kotlinboard.comment

import com.example.kotlinboard.common.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comment")
class CommentController (
    val commentService: CommentService
){
    @GetMapping("")
    fun getComments(boardId: Long): ResponseEntity<Any> {
        return Response.ok(commentService.getComments(boardId))
    }

    @PostMapping("")
    fun createComment(@RequestBody request: CreateCommentRequest): ResponseEntity<Any> {
        return Response.ok(commentService.createComment(request))
    }

    @PatchMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody request: UpdateCommentRequest): ResponseEntity<Any> {
        return Response.ok(commentService.updateComment(id, request))
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long) {
        commentService.delete(id)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException): ResponseEntity<Any> {
        return Response.error(HttpStatus.NOT_FOUND, e.message?: "에러가 발생하였습니다.")
    }
}