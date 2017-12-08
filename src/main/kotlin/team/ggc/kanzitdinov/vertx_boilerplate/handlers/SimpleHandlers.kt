package team.ggc.kanzitdinov.vertx_boilerplate.handlers

import team.ggc.kanzitdinov.vertx_boilerplate.models.SimpleMessage
import team.ggc.kanzitdinov.vertx_boilerplate.services.HomeJsonService
import team.ggc.kanzitdinov.vertx_boilerplate.common.endWithJson
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory
import team.ggc.kanzitdinov.vertx_boilerplate.common.safeLaunch

class SimpleHandlers() {
    private val logger = LoggerFactory.getLogger("VertxServer")

    suspend fun homeJsonHandler(ctx: RoutingContext) {
        safeLaunch(ctx) {
            val homeJsonInfo = HomeJsonService().getResult()

            ctx.response().endWithJson(homeJsonInfo)
        }
    }

    suspend fun helloJsonHandler(ctx: RoutingContext) {
        safeLaunch(ctx) {
            ctx.response().endWithJson(SimpleMessage("Hello, World!"))
        }
    }
}