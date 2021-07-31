package com.thelonedev.plugins

import io.ktor.application.*

fun Application.configureDI() {
    install() {
        SLF4JLogger()
        modules()
    }
}
