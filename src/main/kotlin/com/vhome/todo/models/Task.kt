package com.vhome.todo.models
import com.vhome.todo.plugins.LocalDateSerializer
import kotlinx.serialization.*
import java.time.LocalDate

@Serializable()
data class Task(
    var id: Int? = null, val title: String, val detail: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    val dueDate: LocalDate? = null
)