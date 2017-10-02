package com.kanzitdinov.boilerplate.env

private val DEFAULT_HOST = "localhost"
private val DEFAULT_PORT = 8080

val PORT: Int
    get() {
        val systemPort = System.getenv("PORT")
        when (systemPort) {
            null -> return DEFAULT_PORT
            else -> return Integer.valueOf(systemPort)
        }
    }

val HOST: String
    get() {
        return System.getenv("HOST") ?: DEFAULT_HOST
    }