package com.thelonedev

import com.thelonedev.plugins.configureDI
import com.thelonedev.plugins.configureHTTP
import com.thelonedev.plugins.configureMonitoring
import com.thelonedev.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureMonitoring()
        configureDI()
        configureDatastore()
    }.start(wait = true)
}
