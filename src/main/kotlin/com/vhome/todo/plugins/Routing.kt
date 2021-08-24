package com.vhome.todo.plugins

import com.vhome.todo.createTask
import com.vhome.todo.models.Task
import com.vhome.todo.fetchTasks
import com.vhome.todo.updateTask
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import java.lang.NumberFormatException

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
        put("/task/{id}") {
            val idStr = call.parameters["id"]
            val id = idStr?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)

            val task = call.receive<Task>()
            if (task.id != id) return@put call.respond(HttpStatusCode.BadRequest)

            try {
                updateTask(task)
                call.respond(HttpStatusCode.NoContent)
            } catch (e: Throwable) {
                // TODO: Less naive 404
                call.respond(HttpStatusCode.NotFound, e.localizedMessage.toString())
            }

        }

    }
}
