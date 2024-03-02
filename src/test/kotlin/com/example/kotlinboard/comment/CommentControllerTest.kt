package com.example.kotlinboard.comment

import com.example.kotlinboard.board.BaseControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.JsonFieldType.NUMBER
import org.springframework.restdocs.payload.JsonFieldType.STRING
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

class CommentControllerTest: BaseControllerTest(){

    @Test
    @DisplayName("댓글 목록 조회")
    fun getComments(){
        val comments = listOf(
            CommentListResponse(1L, "comment1", 1L, "user1", LocalDateTime.now(), LocalDateTime.now()),
            CommentListResponse(2L, "comment2", 1L, "user1", LocalDateTime.now(), LocalDateTime.now()),
            CommentListResponse(3L, "comment3", 1L, "user2", LocalDateTime.now(), LocalDateTime.now()),
            CommentListResponse(4L, "comment4", 1L, "user3", LocalDateTime.now(), LocalDateTime.now()),
        )

        Mockito.`when`(commentService.getComments(anyLong())).thenReturn(comments)

        this.mockMvc!!.perform(
            MockMvcRequestBuilders.get("/api/comment?boardId=1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                this.restDocs.document(
                    queryParameters(
                        parameterWithName("boardId").description("게시글 ID")
                    ),
                    responseFields(
                        beneathPath("data").withSubsectionId("data"),
                        fieldWithPath("id").type(NUMBER).description("댓글 ID"),
                        fieldWithPath("content").type(STRING).description("내용"),
                        fieldWithPath("author").type(STRING).description("작성자"),
                        fieldWithPath("createdAt").type(STRING).description("작성일"),
                        fieldWithPath("updatedAt").type(STRING).description("수정일"),
                        fieldWithPath("boardId").type(NUMBER).description("게시글 ID")
                    )
                )
            )
    }


    @Test
    @DisplayName("댓글 작성")
    fun createComment() {
        val request = CreateCommentRequest("content", 1L)

        Mockito.`when`(commentService.createComment(request)).thenReturn(1L)

        this.mockMvc!!.perform(
            MockMvcRequestBuilders.post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
                .content(creatJson(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(
                this.restDocs.document(
                    requestFields(
                        fieldWithPath("content").type(STRING).description("내용"),
                        fieldWithPath("boardId").type(NUMBER).description("게시글 ID")
                    ),
                    relaxedResponseFields(
                        fieldWithPath("data").type(NUMBER).description("생성된 인덱스")
                    )
                )
            )
    }

    @Test
    @DisplayName("댓글 수정")
    fun updateComment() {
        val request = UpdateCommentRequest("title")

        Mockito.`when`(commentService.updateComment(1L, request)).thenReturn(1L)

        this.mockMvc!!.perform(
            RestDocumentationRequestBuilders.patch("/api/comment/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
                .content(creatJson(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                this.restDocs.document(
                    pathParameters(
                        parameterWithName("id").description("댓글 ID")
                    ),
                    requestFields(
                        fieldWithPath("content").type(STRING).description("내용")
                    ),
                    relaxedResponseFields(
                        fieldWithPath("data").type(NUMBER).description("수정된 인덱스")
                    )
                )
            )
    }

    @Test
    @DisplayName("댓글 삭제")
    fun deleteComment() {
        this.mockMvc!!.perform(
            RestDocumentationRequestBuilders.delete("/api/comment/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(
                this.restDocs.document(
                    pathParameters(
                        parameterWithName("id").description("댓글 ID")
                    )
                )
            )
    }

}