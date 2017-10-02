package com.kanzitdinov.boilerplate.routes

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import com.kanzitdinov.boilerplate.handlers.*

val API_ENDPOINT = "/api"

class Routes(val vertx: Vertx) {
    fun createRouter(): Router {
        val ConfigHandlers = ConfigHandlers()
        val SimpleHandlers = SimpleHandlers()
        val TodosHandlers = TodosHandlers()

        return Router.router(vertx).apply {
            // CONFIG ROUTES
            route().handler(ConfigHandlers.corsHandler)
            route().handler(ConfigHandlers.bodyHandler)

            // SIMPLE ROUTES
            get("/home").handler { SimpleHandlers.homeHandler(it) }
            get("/home.json").handler { SimpleHandlers.homeJsonHandler(it) }
            get("/hello").handler { SimpleHandlers.helloHandler(it) }
            get("/hello.json").handler { SimpleHandlers.helloJsonHandler(it) }

            // TODOS ROUTES
            get("$API_ENDPOINT/todos").handler { TodosHandlers.getTodos(it) }
            get("$API_ENDPOINT/todos/:id").handler { TodosHandlers.getTodoById(it) }
            post("$API_ENDPOINT/todos").handler { TodosHandlers.createNewTodo(it) }
            put("$API_ENDPOINT/todos/:id").handler { TodosHandlers.updateTodoById(it) }
//            patch("$API_ENDPOINT/todos/:id")
            delete("$API_ENDPOINT/todos/:id").handler { TodosHandlers.removeTodoById(it) }

            route("/public/*").handler(ConfigHandlers.staticHandler)
            route().handler { ConfigHandlers.otherPageHandler(it) }
        }
    }
}