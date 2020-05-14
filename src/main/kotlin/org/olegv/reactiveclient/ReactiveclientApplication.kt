package org.olegv.reactiveclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication
class ReactiveclientApplication

fun main(args: Array<String>) {
	//Hooks.onOperatorDebug();
	ReactorDebugAgent.init();
	runApplication<ReactiveclientApplication>(*args)
}
