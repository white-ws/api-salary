package com.thelonedev.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatastore() {
    val host = environment.config.property("database.host").getString()
    val port = environment.config.property("database.port").getString()
    val user = environment.config.property("database.user").getString()
    val password = environment.config.property("database.password").getString()
    val dbName = environment.config.property("database.name").getString()

    Database.connect(url = "jdbc:mysql://$host:$port/$dbName",
        driver = "com.mysql.jdbc.Driver",
        user = user,
        password = password
    )
}