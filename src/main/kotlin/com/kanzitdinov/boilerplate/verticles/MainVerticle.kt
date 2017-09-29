package com.kanzitdinov.boilerplate.verticles

import com.kanzitdinov.boilerplate.env.PORT
import com.kanzitdinov.boilerplate.routes.Routes
import io.vertx.core.*;
import io.vertx.ext.web.Router
import org.slf4j.LoggerFactory
import uy.klutter.vertx.VertxInit

class MainVerticle: AbstractVerticle() {

    override fun start(startFuture: Future<Void>?) {
        VertxInit.ensure()

        val vertx = Vertx.vertx()
        val server = vertx.createHttpServer()
        val router = Routes(vertx).createRouter()
        val logger = LoggerFactory.getLogger("VertxServer")

        server
                .requestHandler { router.accept(it) }
                .listen(PORT, { handler ->
                    if (!handler.succeeded()) {
                        logger.error("Failed to listen on port 8080")
                    }
                })
    }

}
