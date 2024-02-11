package com.example.kotlinboard.board

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

class BoardControllerTest : BaseControllerTest() {

    @Test
    @DisplayName("게시글 목록 조회")
    fun getBoards(){
        val boards = listOf(
            BoardListResponse(1L, "title1", 0L, "user1", LocalDateTime.now()),
            BoardListResponse(2L, "title2", 1L, "user2", LocalDateTime.now()),
        )
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(boards)

        Mockito.`when`(boardService.getBoards(pageable)).thenReturn(page)

        this.mockMvc!!.perform(
            MockMvcRequestBuilders.get("/api/board?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                this.restDocs.document(
                    responseFields(
                        beneathPath("data.content").withSubsectionId("data"),
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 ID"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("view").type(JsonFieldType.NUMBER).description("조회수"),
                        fieldWithPath("author").type(JsonFieldType.STRING).description("작성자"),
                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성일")
                    )
                )
            )
    }

    @Test
    @DisplayName("게시글 조회")
    fun getBoard() {
        val board = BoardDetailResponse(1L, "title", "content", 16L, "user1", LocalDateTime.now())

        Mockito.`when`(boardService.getBoard(anyLong())).thenReturn(board)

        this.mockMvc!!.perform(
            RestDocumentationRequestBuilders.get("/api/board/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                this.restDocs.document(
                    pathParameters(
                        parameterWithName("id").description("게시글 ID")
                    ),
                    responseFields(
                        beneathPath("data").withSubsectionId("data"),
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 ID"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("view").type(JsonFieldType.NUMBER).description("조회수"),
                        fieldWithPath("author").type(JsonFieldType.STRING).description("작성자"),
                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성일")
                    )
                )
            )
    }


    @Test
    @DisplayName("게시글 작성")
    fun createBoard() {
        val request = CreateBoardRequest("title", "content")

        Mockito.`when`(boardService.createBoard(request)).thenReturn(1L)

        this.mockMvc!!.perform(
            MockMvcRequestBuilders.post("/api/board")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
                .content(creatJson(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(
                this.restDocs.document(
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                    ),
                    relaxedResponseFields(
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("생성된 인덱스")
                    )
                )
            )
    }

    @Test
    @DisplayName("게시글 수정")
    fun updateBoard() {
        val request = UpdateBoardRequest("title", "content")

        Mockito.`when`(boardService.updateBoard(1L, request)).thenReturn(1L)

        this.mockMvc!!.perform(
            RestDocumentationRequestBuilders.patch("/api/board/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
                .content(creatJson(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                this.restDocs.document(
                    pathParameters(
                        parameterWithName("id").description("게시글 ID")
                    ),
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                    ),
                    relaxedResponseFields(
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("수정된 인덱스")
                    )
                )
            )
    }

    @Test
    @DisplayName("게시글 삭제")
    fun deleteBoard() {
        this.mockMvc!!.perform(
            RestDocumentationRequestBuilders.delete("/api/board/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", bearerToken)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }


}