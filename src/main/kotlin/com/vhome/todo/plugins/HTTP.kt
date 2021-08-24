package com.vhome.todo.plugins

import io.ktor.features.*
import io.ktor.application.*
import io.ktor.serialization.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
// TODO: review this
object LocalDateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val result = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        encoder.encodeString(result)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
//    If use `@Contextual` within the class definition, then can define how that works with the code below.
//    json(Json {
//        serializersModule = SerializersModule {
//            contextual(LocalDate::class, LocalDateSerializer)
//        }
//    })
}

