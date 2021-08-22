package com.vhome.todo.plugins

import io.ktor.features.*
import io.ktor.application.*
import io.ktor.serialization.*

fun Application.configureHTTP() {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
