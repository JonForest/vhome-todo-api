package com.vhome.todo

import com.vhome.todo.plugins.*
import io.ktor.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    configureRouting()
    configureHTTP()
    configureSerialization()
}
