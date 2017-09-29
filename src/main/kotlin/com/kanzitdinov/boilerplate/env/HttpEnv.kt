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

val PUBLIC_PORT: Int
    get() {
        val systemPort = System.getenv("PORT")
        when (systemPort) {
            null -> return DEFAULT_PORT
            else -> return 80 // means we're on the cloud
        }
    }


val HOST: String
    get() {
        return System.getenv("HOST") ?: DEFAULT_HOST
    }