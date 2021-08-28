package com.thelonedev.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header(HttpHeaders.Server, "Salary-API-Webserver")
    }
    install(CORS) {
        method(HttpMethod.Options)
        allowCredentials = true
        allowNonSimpleContentTypes = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

}
