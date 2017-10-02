package com.kanzitdinov.boilerplate.handlers

import com.fasterxml.jackson.module.kotlin.*
import com.kanzitdinov.boilerplate.models.Todo
import com.kanzitdinov.boilerplate.services.TodosService
import io.vertx.ext.web.RoutingContext
import io.vertx.core.http.HttpHeaders.CONTENT_TYPE
import io.vertx.core.json.Json
import java.util.*

fun RoutingContext.toJson(obj: Any?) {
    response()
            .putHeader(CONTENT_TYPE, "application/json; charset=utf-8")
            .end(Json.encodePrettily(obj))
}

class TodosHandlers() {
    private val TodosService = TodosService()
    private val jsonMapper = jacksonObjectMapper()

    val getTodos = { ctx: RoutingContext ->
        val todos = TodosService.find()

        ctx.toJson(todos)
    }

    val getTodoById = { ctx: RoutingContext ->
        val _id = UUID.fromString(ctx.request().getParam("id"))

        val _todo = TodosService.get(_id)

        ctx.toJson(_todo)
    }

    val createNewTodo = { ctx: RoutingContext ->
        val tmpTodo: Todo = jsonMapper.readValue(ctx.bodyAsString)
        val newTodo: Todo = Todo(title = tmpTodo.title, completed = tmpTodo.completed)

        val _todo = TodosService.create(newTodo)

        ctx.toJson(_todo)
    }

    val updateTodoById = { ctx: RoutingContext ->
        val _id = UUID.fromString(ctx.request().getParam("id"))
        val newTodo: Todo = jsonMapper.readValue(ctx.bodyAsString)

        val _todo = TodosService.update(_id, newTodo)

        ctx.toJson(_todo)
    }

    val removeTodoById = { ctx: RoutingContext ->
        val _id = UUID.fromString(ctx.request().getParam("id"))

        val _todo = TodosService.remove(_id)

        ctx.toJson(_todo)
    }
}