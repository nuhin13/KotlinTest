package test.kotlin.nuhin13.kotlintest.api.response

import com.squareup.moshi.Json

data class PreferTime (val message: String,
                       val code: Int,
                       var times: List<Time>? = null)