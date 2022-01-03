package eu.withoutaname.api

import eu.withoutaname.api.plugins.configureMonitoring
import eu.withoutaname.api.plugins.configureRouting
import eu.withoutaname.api.plugins.configureSecurity
import eu.withoutaname.api.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureMonitoring()
        configureSerialization()
    }.start(wait = true)
}
