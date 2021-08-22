package com.vhome.todo.models
import java.util.*
import kotlinx.serialization.Serializable

@Serializable
data class Task(var id: Int? = null, val title: String, val detail: String? = null, val dueDate: String? = null)