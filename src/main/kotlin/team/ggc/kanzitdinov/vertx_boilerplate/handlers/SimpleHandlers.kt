package team.ggc.kanzitdinov.vertx_boilerplate.handlers

import team.ggc.kanzitdinov.vertx_boilerplate.models.SimpleMessage
import team.ggc.kanzitdinov.vertx_boilerplate.services.HomeJsonService
import team.ggc.kanzitdinov.vertx_boilerplate.common.endWithJson
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory

class SimpleHandlers() {
    private val logger = LoggerFactory.getLogger("VertxServer")

    suspend fun homeJsonHandler(ctx: RoutingContext) {
        val homeJsonInfo = HomeJsonService().getResult()

        ctx.response().endWithJson(homeJsonInfo)
    }

    suspend fun helloJsonHandler(ctx: RoutingContext) {
        ctx.response().endWithJson(SimpleMessage("Hello, World!"))
    }
}