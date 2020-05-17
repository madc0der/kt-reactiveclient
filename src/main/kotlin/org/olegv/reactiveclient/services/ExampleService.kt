package org.olegv.reactiveclient.services

import org.olegv.reactiveclient.models.RestResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Service
class ExampleService {

    @Autowired
    private lateinit var exampleLowLevelService: ExampleLowLevelService;

    companion object {
        @JvmStatic
        private val log = Logger.getLogger(ExampleService::class.simpleName)
    }

    fun callRestAPI(): Mono<RestResponse> {
        log.info("Trying to call external api...")
        return WebClient.create()
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
                .retrieve()
                .bodyToMono(RestResponse::class.java)
    }

    fun callRestAPIWrongUrl(): Mono<RestResponse> {
        log.info("Trying to call external api...")
        return WebClient.create()
                .get()
                //Non json response, see ReactorDebugAgent in action
                .uri("https://jsonplaceholder.typicode.com")
                .retrieve()
                .bodyToMono(RestResponse::class.java)
    }

    fun callLowLevelService(): Int {
        return exampleLowLevelService.getValue();
    }
}