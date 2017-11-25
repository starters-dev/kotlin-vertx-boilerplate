package team.ggc.kanzitdinov.vertx_boilerplate.services

import team.ggc.kanzitdinov.vertx_boilerplate.common.updateWhere
import team.ggc.kanzitdinov.vertx_boilerplate.models.Todo
import java.util.*

class TodosService() {
    private var todos: MutableList<Todo> = mutableListOf<Todo>()

    suspend fun find(/*params: Map<String, String>?*/): List<Todo>? {
        return todos
    }

    suspend fun get(id: UUID/*, params: Map<String, String>?*/): Todo? {
        return todos.find { it.id == id }
    }

    suspend fun create(data: Todo): Todo? {
        todos.add(data)

        return data
    }

    suspend fun update(id: UUID, data: Todo): Todo? {
        val todo = Todo(id = id, completed = data.completed, title = data.title)
        todos.updateWhere({ it.id == id }, todo)

        return todo
    }

    suspend fun remove(id: UUID): Todo? {
        val todo = todos.find { it.id == id }
        todos.remove(todo)

        return todo
    }
}