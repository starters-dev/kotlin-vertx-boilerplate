package team.ggc.kanzitdinov.vertx_boilerplate.handlers

import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.StaticHandler

class ConfigHandlers() {
    val corsHandler = CorsHandler.create("*")
            .allowedHeader(HttpHeaders.CONTENT_TYPE.toString())
            .allowedMethods(setOf(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS))
    val bodyHandler = BodyHandler.create()
    val staticHandler = StaticHandler.create().setWebRoot("public")

    val otherPageHandler = { ctx: RoutingContext ->
        val response = ctx.response()
        response.end("Just a plain page")
    }
}