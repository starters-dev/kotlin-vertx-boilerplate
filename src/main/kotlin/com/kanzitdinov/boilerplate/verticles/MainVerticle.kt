package com.kanzitdinov.boilerplate.verticles

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kanzitdinov.boilerplate.models.*
import com.kanzitdinov.boilerplate.services.HomeJsonService
import io.vertx.core.*;
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.templ.ThymeleafTemplateEngine
import nl.komponents.kovenant.task
import org.slf4j.LoggerFactory
import uy.klutter.vertx.VertxInit
import java.text.SimpleDateFormat
import java.util.*

fun HttpServerResponse.endWithJson(obj: Any) {
    putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(obj))
}

class MainVerticle: AbstractVerticle() {

    override fun start(startFuture: Future<Void>?) {
        VertxInit.ensure()

        val vertx = Vertx.vertx()
        val server = vertx.createHttpServer()
        val router = Router.router(vertx)

        val templateEngine = ThymeleafTemplateEngine.create()
        val logger = LoggerFactory.getLogger("VertxServer")
        val jsonMapper = jacksonObjectMapper()

        val serverConfig = jsonMapper.readValue(config().getJsonObject("server").encode(), ServerConfig::class.java)
        val serverPort = serverConfig.port
        val enableCaching = serverConfig.caching

        val staticHandler = StaticHandler.create().setWebRoot("public").setCachingEnabled(enableCaching)

        router.get("/home").handler { ctx ->
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

        router.get("/home.json").handler { ctx ->
            val response = ctx.response()

            val asyncTask = HomeJsonService().getResult()
            asyncTask.success { homeJsonInfo ->
                val json = jsonMapper.writeValueAsString(homeJsonInfo)
                response.end(json)
            }
        }

        router.get("/hello").handler { ctx ->
            val response = ctx.response()
            response.end("Hello, World!")
        }

        router.get("/hello.json").handler { ctx ->
            val response = ctx.response()
            response.endWithJson(SimpleMessage("Hello, World!"))
        }

        router.route("/public/*").handler(staticHandler)

        router.route().handler { ctx ->
            val response = ctx.response()
            response.end("Just a plain page")
        }

        server
                .requestHandler { router.accept(it) }
                .listen(serverPort, { handler ->
                    if (!handler.succeeded()) {
                        logger.error("Failed to listen on port 8080")
                    }
                })
    }

}
