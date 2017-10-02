package com.kanzitdinov.boilerplate.services

import com.kanzitdinov.boilerplate.models.Todo
import java.util.*

fun<T> MutableList<T>.updateWhere(finder: (T) -> Boolean, newValue: T): Int {
    val idx = indexOf(find(finder))
    if (idx > -1) set(idx, newValue)
    return idx
}

class TodosService() {
    private var todos: MutableList<Todo> = mutableListOf<Todo>()

    fun find(/*params: Map<String, String>?*/): List<Todo>? {
        return todos
    }

    fun get(id: UUID/*, params: Map<String, String>?*/): Todo? {
        return todos.find { it.id == id }
    }

    fun create(data: Todo): Todo? {
        todos.add(data)

        return data
    }

    fun update(id: UUID, data: Todo): Todo? {
        val todo = Todo(id = id, completed = data.completed, title = data.title)
        todos.updateWhere({ it.id == id }, todo)

        return todo
    }

//    fun patch(id: UUID, data: Map<String, Any>): Todo {
//        val DEFAULT_TODO = Todo(UUID.randomUUID(), "Default TODO", false)
//
//        return DEFAULT_TODO
//    }

    fun remove(id: UUID): Todo? {
        val todo = todos.find { it.id == id }
        todos.remove(todo)

        return todo
    }
}