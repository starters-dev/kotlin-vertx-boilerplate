package team.ggc.kanzitdinov.vertx_boilerplate.routes

import team.ggc.kanzitdinov.vertx_boilerplate.common.coroutineHandler
import team.ggc.kanzitdinov.vertx_boilerplate.handlers.*
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

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
            get("/home").coroutineHandler { SimpleHandlers.homeJsonHandler(it) }
            get("/home.json").coroutineHandler { SimpleHandlers.homeJsonHandler(it) }
            get("/hello").coroutineHandler { SimpleHandlers.helloJsonHandler(it) }
            get("/hello.json").coroutineHandler { SimpleHandlers.helloJsonHandler(it) }

            // TODOS ROUTES
            get("${API_ENDPOINT}/todos").coroutineHandler { TodosHandlers.getTodos(it) }
            get("${API_ENDPOINT}/todos/:id").coroutineHandler { TodosHandlers.getTodoById(it) }
            post("${API_ENDPOINT}/todos").coroutineHandler { TodosHandlers.createNewTodo(it) }
            put("${API_ENDPOINT}/todos/:id").coroutineHandler { TodosHandlers.updateTodoById(it) }
            delete("${API_ENDPOINT}/todos/:id").coroutineHandler { TodosHandlers.removeTodoById(it) }

            // route("/public/*").handler(ConfigHandlers.staticHandler)
            route().handler { ConfigHandlers.otherPageHandler(it) }
        }
    }
}