package com.kanzitdinov.boilerplate.handlers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kanzitdinov.boilerplate.models.SimpleMessage
import com.kanzitdinov.boilerplate.services.HomeJsonService
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.ThymeleafTemplateEngine
import nl.komponents.kovenant.task
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*

fun HttpServerResponse.endWithJson(obj: Any) {
    putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(obj))
}

class SimpleHandlers() {
    private val templateEngine = ThymeleafTemplateEngine.create()
    private val logger = LoggerFactory.getLogger("VertxServer")
    private val jsonMapper = jacksonObjectMapper()

    val homeHandler = { ctx: RoutingContext ->
        val timeP = task { SimpleDateFormat().format(Date()) }

        timeP.success { time ->
            ctx.put("time", time)

            templateEngine.render(ctx, "public/templates/index.html", { buf ->
                if (buf.failed()) {
                    logger.error("Template rendering failed", buf.cause())
                } else {
                    val response = ctx.response()
                    response.end(buf.result())
                }
            })
        }
    }

    val homeJsonHandler = { ctx: RoutingContext ->
        val response = ctx.response()

        val asyncTask = HomeJsonService().getResult()
        asyncTask.success { homeJsonInfo ->
            val json = jsonMapper.writeValueAsString(homeJsonInfo)
            response.end(json)
        }
    }

    val helloHandler = { ctx: RoutingContext ->
        val response = ctx.response()
        response.end("Hello, World!")
    }

    val helloJsonHandler = { ctx: RoutingContext ->
        val response = ctx.response()
        response.endWithJson(SimpleMessage("Hello, World!"))
    }
}