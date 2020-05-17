package org.olegv.reactiveclient

import org.olegv.reactiveclient.models.RestResponse
import org.olegv.reactiveclient.services.ExampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ExampleController(private val exampleService: ExampleService) {

    @GetMapping("/call-api")
    fun callApi(): Mono<RestResponse> {
        return exampleService.callRestAPI()
    }

    @GetMapping("/call-api-error")
    fun callApiError(): Mono<RestResponse> {
        return exampleService.callRestAPIWrongUrl()
    }

    @GetMapping("/test-handler")
    fun testHandler(): Int {
        return exampleService.callLowLevelService();
    }
}