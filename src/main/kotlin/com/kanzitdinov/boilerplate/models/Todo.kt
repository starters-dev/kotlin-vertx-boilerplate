package com.kanzitdinov.boilerplate.models

import com.kanzitdinov.boilerplate.env.HOST
import com.kanzitdinov.boilerplate.env.PORT

data class Todo(
        var id: String?,
        val title: String?,
        var order: Int?,
        val completed: Boolean = false
) {
    var url : String = ""
        get() { return "http://$HOST:$PORT/api/todos/${id}" }
}