package com.kanzitdinov.boilerplate.routes

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import com.kanzitdinov.boilerplate.handlers.*

class Routes(val vertx: Vertx) {
    fun createRouter(): Router {
        val ConfigHandlers = ConfigHandlers()
        val SimpleHandlers = SimpleHandlers()

        return Router.router(vertx).apply {
            // CONFIG ROUTES
            route().handler(ConfigHandlers.corsHandler)
            route().handler(ConfigHandlers.bodyHandler)

            // SIMPLE ROUTES
            get("/home").handler { SimpleHandlers.homeHandler(it) }
            get("/home.json").handler { SimpleHandlers.homeJsonHandler(it) }
            get("/hello").handler { SimpleHandlers.helloHandler(it) }
            get("/hello.json").handler { SimpleHandlers.helloJsonHandler(it) }


            route("/public/*").handler(ConfigHandlers.staticHandler)
            route().handler { ConfigHandlers.otherPageHandler(it) }
        }
    }
}