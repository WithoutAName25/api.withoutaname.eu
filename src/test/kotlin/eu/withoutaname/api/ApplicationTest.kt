package eu.withoutaname.api

import eu.withoutaname.api.plugins.configureRouting
import io.ktor.http.*
import io.ktor.server.locations.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @KtorExperimentalLocationsAPI
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}