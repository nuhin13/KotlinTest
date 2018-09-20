package test.kotlin.nuhin13.kotlintest

import com.squareup.moshi.Json

data class ApiModel(val message: String,
                    val code: Long,
                    val order: Order,
                    val jobs: Jobs,
                    val version: String)

data class Jobs(    val id: Long,
                    val status: String)

data class Order(   val id: Long,
                    val customer_mobile: String,
                    val customer_name: String)

class PreferTime(     @Json(name = "message") val message: String,
                      @Json(name = "code") val code: Int,
                      @Json(name = "times") val time: ArrayList<Time>)

class Time(     @Json(name = "key") val key: String,
                @Json(name = "value") val value: String,
                @Json(name = "isValid") val isValid: String)