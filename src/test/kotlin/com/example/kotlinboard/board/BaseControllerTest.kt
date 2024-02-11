package com.example.kotlinboard.board

import com.example.kotlinboard.authentication.AuthenticationService
import com.example.kotlinboard.authentication.SignInDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@SpringBootTest
abstract class BaseControllerTest {

    protected var bearerToken: String? = null
    protected var mockMvc: MockMvc? = null
    protected val restDocs = restDocs()


    @Autowired
    protected lateinit var authenticationService: AuthenticationService

    @MockBean
    protected lateinit var boardService: BoardService
    val objectMapper = ObjectMapper()

    @BeforeEach
    fun setUp(webApplicationContext: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        val token = authenticationService.signIn(SignInDto("user1@cawave.co.kr", "qwe123!@#"))
        bearerToken = "Bearer $token"

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .alwaysDo<DefaultMockMvcBuilder>(this.restDocs)
            .build()
    }

    protected fun creatJson(request: Any): String = objectMapper.writeValueAsString(request)



    fun restDocs(): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            "{class-name}/{method-name}",
            getDocumentRequest(),
            getDocumentResponse()
        )
    }

    fun getDocumentRequest(): OperationRequestPreprocessor {
        return Preprocessors.preprocessRequest(
            Preprocessors.modifyUris()
                .scheme("http")
                .host("localhost"),
            Preprocessors.prettyPrint(),
        )
    }

    fun getDocumentResponse(): OperationResponsePreprocessor {
        return Preprocessors.preprocessResponse(
            Preprocessors.prettyPrint()
        )
    }

}