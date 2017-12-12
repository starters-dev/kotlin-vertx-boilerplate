package team.ggc.kanzitdinov.vertx_boilerplate.services

import team.ggc.kanzitdinov.vertx_boilerplate.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.gildor.coroutines.retrofit.await
import java.text.SimpleDateFormat
import java.util.*

class HomeJsonService {
    suspend fun getResult(): HomeJsonResponse {
        val api = Api.create()

        val response = api.getJsonData().await()
        val message = response.message
        val data = response.data
        val time = SimpleDateFormat().format(Date())

        return HomeJsonResponse(message, data, time)
    }

    private data class JsonData(val message: String, val data: List<Int>)
    private interface Api {

        @GET("6qk2h")
        fun getJsonData(): Call<JsonData>

        companion object Factory {
            fun create(): Api {
                val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.myjson.com/bins/")
                        .build()

                return retrofit.create(Api::class.java);
            }
        }
    }
}