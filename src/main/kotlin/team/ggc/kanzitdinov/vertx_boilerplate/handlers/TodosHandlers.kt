package team.ggc.kanzitdinov.vertx_boilerplate.handlers

import team.ggc.kanzitdinov.vertx_boilerplate.common.endWithJson
import team.ggc.kanzitdinov.vertx_boilerplate.models.Todo
import team.ggc.kanzitdinov.vertx_boilerplate.services.TodosService
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.vertx.ext.web.RoutingContext
import team.ggc.kanzitdinov.vertx_boilerplate.common.safeLaunch
import java.util.*

class TodosHandlers() {
    private val TodosService = TodosService()
    private val gson = Gson()

    suspend fun getTodos(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val todos = TodosService.find()

            ctx.response().endWithJson(todos)
        }
    }

    suspend fun getTodoById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))

            val _todo = TodosService.get(_id)

            ctx.response().endWithJson(_todo)
        }
    }

    suspend fun createNewTodo(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val tmpTodo: Todo = gson.fromJson(ctx.bodyAsString)
            val newTodo: Todo = Todo(title = tmpTodo.title, completed = tmpTodo.completed)

            val _todo = TodosService.create(newTodo)

            ctx.response().endWithJson(_todo)
        }
    }

    suspend fun updateTodoById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))
            val newTodo: Todo = gson.fromJson(ctx.bodyAsString)

            val _todo = TodosService.update(_id, newTodo)

            ctx.response().endWithJson(_todo)
        }
    }

    suspend fun removeTodoById(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val _id = UUID.fromString(ctx.request().getParam("id"))

            val _todo = TodosService.remove(_id)

            ctx.response().endWithJson(_todo)
        }
    }
}