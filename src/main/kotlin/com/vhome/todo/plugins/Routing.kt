package com.vhome.todo.plugins

import com.vhome.todo.models.Task
import com.vhome.todo.fetchTasks
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/tasks") {
            call.respond(fetchTasks())
        }
        post("/task") {
            val task = call.receive<Task>()
            println(task)
            call.respond(201)
        }

    }
}
