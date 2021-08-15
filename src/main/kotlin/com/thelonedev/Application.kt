package com.thelonedev

import com.thelonedev.plugins.*
import io.ktor.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.modules() {
    configureRouting()
    configureHTTP()
    configureMonitoring()
    configureDI()
    configureDatastore()
}
