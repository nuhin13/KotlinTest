package test.kotlin.nuhin13.kotintest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiService {

    @GET("v2/partners/{partnerId}/orders/{orderId}")
    fun getOrderInfo(
            @Path("partnerId") partnerId:Int,
            @Path("orderId") orderId: Int,
            @Query("remember_token") rememberToken: String,
            @Query("filter") filter: String): Observable<Result>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.sheba.xyz/")
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}