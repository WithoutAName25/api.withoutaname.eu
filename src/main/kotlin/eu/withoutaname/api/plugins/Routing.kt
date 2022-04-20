package eu.withoutaname.api.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

@KtorExperimentalLocationsAPI
fun Application.configureRouting() {
    install(Locations)
    install(StatusPages) {
        exception<AuthenticationException> { call, _ ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, _ ->
            call.respond(HttpStatusCode.Forbidden)
        }
    }
    routing {
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
