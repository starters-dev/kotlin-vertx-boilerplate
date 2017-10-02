package com.kanzitdinov.boilerplate.models

import com.kanzitdinov.boilerplate.env.HOST
import com.kanzitdinov.boilerplate.env.PORT
import java.util.*

data class Todo(
        var id: UUID? = UUID.randomUUID(),
        val title: String?,
        val completed: Boolean = false
) {
    var url : String = ""
        get() { return "http://$HOST:$PORT/api/todos/${id}" }
}