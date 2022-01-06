package eu.withoutaname.api

import eu.withoutaname.api.database.DatabaseHelper
import eu.withoutaname.api.plugins.configureMonitoring
import eu.withoutaname.api.plugins.configureRouting
import eu.withoutaname.api.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.locations.*
import io.ktor.server.netty.*

@KtorExperimentalLocationsAPI
fun main() {
    DatabaseHelper.setup()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureMonitoring()
        configureSerialization()
    }.start(wait = true)
}
