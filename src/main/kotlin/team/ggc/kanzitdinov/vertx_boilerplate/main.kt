package team.ggc.kanzitdinov.vertx_boilerplate

import team.ggc.kanzitdinov.vertx_boilerplate.verticles.MainVerticle
import io.vertx.core.Vertx

fun main(args : Array<String>) {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(MainVerticle()) { ar ->
        if (ar.succeeded()) {
            println("Application started")
        } else {
            println("Could not start application")
            ar.cause().printStackTrace()
        }
    }
}