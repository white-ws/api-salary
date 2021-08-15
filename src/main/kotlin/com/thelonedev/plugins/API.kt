package com.thelonedev.plugins

import entities.AddSalaryRequest
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import usecases.AddSalary
import usecases.ListSalaries

fun Route.salaryRoutes() {
    val listSalaries: ListSalaries by inject()
    val addSalary: AddSalary by inject()

    route("/salaries") {

        // list
        get {
            call.respond(listSalaries())
        }

        // create
        post {
            val request = call.receive<AddSalaryRequest>()
            addSalary(request)
        }
    }
}
