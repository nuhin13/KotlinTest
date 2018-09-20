package test.kotlin.nuhin13.kotlintest

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("v2/partners/{partnerId}/orders/{orderId}")
    fun getOrderInfo(
            @Path("partnerId") partnerId:Int,
            @Path("orderId") orderId: Int,
            @Query("remember_token") rememberToken: String)

    @GET("v2/times?for=app")
    fun getPreferTime(): Observable<PreferTime>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {

        var client: OkHttpClient? = null

        fun create(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            client = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://api.sheba.xyz/")
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}