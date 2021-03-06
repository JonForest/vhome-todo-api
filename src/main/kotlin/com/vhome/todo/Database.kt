package com.vhome.todo

import org.ktorm.schema.*
import org.ktorm.database.*
import org.ktorm.dsl.*
import com.vhome.todo.models.*

val database =
    Database.connect(
        "jdbc:postgresql://localhost:${dotenv["PG_PORT"]}/${dotenv["PG_DBNAME"]}",
        user = dotenv["PG_USER"],
        password = dotenv["PG_PASSWORD"]
    )

object TaskTable : Table<Nothing>("task", schema = "tasks") {
    val id = int("id").primaryKey()
    val title = varchar("title")
    val detail = varchar("detail")
    var dueDate = date("due_date") //  If I set this to type date, then it fails when I try to set with type mismatch
}

fun fetchTasks(): List<Task> {
    // TODO: https://www.ktorm.org/en/define-entities-as-any-kind-of-classes.html#Use-Data-Class
    val tasks = database.from(TaskTable).select().map {
        Task(
            id = it[TaskTable.id],
            title = it[TaskTable.title] ?: "",
            detail = it[TaskTable.detail] ?: "",
            dueDate = it[TaskTable.dueDate]
        )
    }
    return tasks
}

fun createTask(task: Task): Int {
    return database.insert(TaskTable) {
        set(it.title, task.title)
        set(it.detail, task.detail)
        set(it.dueDate, task.dueDate)
    }
}

fun updateTask(task: Task): Int {
    if (task.id == null) throw Error("Invalid task passed to updateTask")

    return database.update(TaskTable) {
        set(it.title, task.title)
        set(it.detail, task.detail)
        set(it.dueDate, task.dueDate)
        where {
            it.id eq task.id!!
        }
    }
}