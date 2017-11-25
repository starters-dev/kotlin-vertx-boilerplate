package team.ggc.kanzitdinov.vertx_boilerplate.models

import team.ggc.kanzitdinov.vertx_boilerplate.common.HOST
import team.ggc.kanzitdinov.vertx_boilerplate.common.PORT
import java.util.*

data class Todo(
        var id: UUID? = UUID.randomUUID(),
        val title: String?,
        val completed: Boolean = false
) {
    var url : String = ""
        get() { return "http://${HOST}:${PORT}/api/todos/${id}" }
}