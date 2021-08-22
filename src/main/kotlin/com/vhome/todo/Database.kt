package com.vhome.todo

import org.ktorm.schema.*
import org.ktorm.database.*
import org.ktorm.dsl.*
import com.vhome.todo.models.*

val database =
    Database.connect("jdbc:postgresql://localhost:5432/vhome", user = "tasks_user", password = "tasks_password")

object TaskTable : Table<Nothing>("task", schema="tasks") {
    val id = int("id").primaryKey()
    val title = varchar("title")
    val detail = varchar("detail")
}

fun fetchTasks(): List<Task> {
    // TODO: https://www.ktorm.org/en/define-entities-as-any-kind-of-classes.html#Use-Data-Class
    val tasks = database.from(TaskTable).select().map {
        Task(
            id = it[TaskTable.id],
            title = it[TaskTable.title] ?: "",
            detail = it[TaskTable.detail] ?: ""
        )
    }
    return tasks
}

fun createTask() {
    //TODO
}