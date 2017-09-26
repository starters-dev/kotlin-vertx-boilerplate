package com.kanzitdinov.boilerplate.services

import com.github.kittinunf.fuel.httpGet
import com.github.salomonbrys.kotson.*
import com.google.gson.JsonParser
import nl.komponents.kovenant.*
import com.kanzitdinov.boilerplate.models.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

class HomeJsonService {
    fun getResult(): Promise<HomeJsonResponse, Exception> = task {
        val url = "https://api.myjson.com/bins/6qk2h"

        val (_, response) = url.httpGet().responseString()
        val jsonStr = String(response.data, Charset.forName("UTF-8"))

        val json = JsonParser().parse(jsonStr).obj
        val message = json["message"].string
        val data = json["data"].array.map { it.asInt }
        val time = SimpleDateFormat().format(Date())

        HomeJsonResponse(message, data, time)
    }
}