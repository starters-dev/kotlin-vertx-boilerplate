package team.ggc.kanzitdinov.vertx_boilerplate.verticles

import team.ggc.kanzitdinov.vertx_boilerplate.common.PORT
import team.ggc.kanzitdinov.vertx_boilerplate.routes.Routes
import io.vertx.core.*;
import io.vertx.core.http.HttpServer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult
import org.slf4j.LoggerFactory

class MainVerticle: CoroutineVerticle() {

    suspend override fun start() {
        val vertx = Vertx.vertx()
        val router = Routes(vertx).createRouter()
        val logger = LoggerFactory.getLogger("VertxServer")

        awaitResult<HttpServer> {
            vertx.createHttpServer()
                    .requestHandler(router::accept)
                    .listen(PORT, it)
        }
    }

}
