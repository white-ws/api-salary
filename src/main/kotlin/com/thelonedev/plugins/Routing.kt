package com.thelonedev.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
    install(ContentNegotiation) {
        json()
    }
    routing {
        salaryRoutes()
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
