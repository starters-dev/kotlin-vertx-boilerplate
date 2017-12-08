package team.ggc.kanzitdinov.vertx_boilerplate.common

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch
import team.ggc.kanzitdinov.vertx_boilerplate.models.CommonServiceError

fun HttpServerResponse.endWithJson(obj: Any?, statusCode: Int = 200) {
    setStatusCode(statusCode)
    putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(obj))
}

fun<T> MutableList<T>.updateWhere(finder: (T) -> Boolean, newValue: T): Int {
    val idx = indexOf(find(finder))
    if (idx > -1) set(idx, newValue)
    return idx
}

fun Route.coroutineHandler(fn : suspend (RoutingContext) -> Unit) {
    handler { ctx ->
        launch(ctx.vertx().dispatcher()) {
            try {
                fn(ctx)
            } catch(e: Exception) {
                ctx.fail(e)
            }
        }
    }
}

fun <T> safeLaunch(ctx: RoutingContext, body: suspend () -> T) {
    launch(ctx.vertx().dispatcher()) {
        try {
            body()
        } catch (e: Exception) {
            ctx.response().endWithJson(CommonServiceError(e.localizedMessage, 501))
        }
    }
}