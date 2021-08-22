package com.vhome.todo.plugins

import com.vhome.todo.createTask
import com.vhome.todo.models.Task
import com.vhome.todo.fetchTasks
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
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
            val id = createTask(task)
            call.respond(HttpStatusCode.Created, mapOf<String,Any>("id" to id))
        }

    }
}
