package com.thelonedev.plugins

import io.ktor.application.*
import org.koin.dsl.module
import org.koin.dsl.single
import org.koin.ktor.ext.Koin
import usecases.AddSalary
import usecases.ListSalaries

fun Application.configureDI() {
    install(Koin) {
        modules(
            module {
                single<AddSalary>()
                single<ListSalaries>()
            }
        )
    }
}
