package org.olegv.reactiveclient.services

import org.olegv.reactiveclient.models.RestResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Hooks
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Service
class ExampleService {

    companion object {
        @JvmStatic
        private val log = Logger.getLogger(ExampleService::class.simpleName)
    }

    fun callRestAPI(): Mono<RestResponse> {
        val webClient = WebClient.create();
        log.info("Trying to call external api...")
        return webClient
                .get()
                //.uri("https://jsonplaceholder.typicode.com/posts/1")
                .uri("https://jsonplaceholder.typicode.com")
                .retrieve()
                //.onStatus({_ -> true}, {_ -> null})
                .bodyToMono(RestResponse::class.java)
    }
}