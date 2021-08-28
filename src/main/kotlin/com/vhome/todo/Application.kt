package com.vhome.todo

import com.vhome.todo.plugins.configureHTTP
import com.vhome.todo.plugins.configureRouting
import com.vhome.todo.plugins.configureSerialization
import io.github.cdimascio.dotenv.dotenv
import io.ktor.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    configureRouting()
    configureHTTP()
    configureSerialization()
}

val dotenv = dotenv()