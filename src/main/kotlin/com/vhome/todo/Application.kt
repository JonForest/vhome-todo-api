package com.vhome.todo

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.vhome.todo.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
    }.start(wait = true)
}
