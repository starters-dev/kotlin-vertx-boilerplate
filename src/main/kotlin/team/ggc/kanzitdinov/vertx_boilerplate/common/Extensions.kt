package team.ggc.kanzitdinov.vertx_boilerplate.common

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch

fun HttpServerResponse.endWithJson(obj: Any?) {
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