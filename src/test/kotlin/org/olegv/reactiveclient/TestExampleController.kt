package org.olegv.reactiveclient

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.olegv.reactiveclient.services.ExampleLowLevelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestExampleController(@Autowired val restTemplate: TestRestTemplate) {

    @SpyBean
    private lateinit var exampleLowLevelService: ExampleLowLevelService

    @BeforeEach
    fun beforeClass() {
        `when`(exampleLowLevelService.getValue()).thenReturn(42);
    }

    @Test
    fun testCallHandlerToLowLevelService() {
        val entity = restTemplate.getForEntity<Int>("/test-handler")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).isEqualTo(42)
    }
}