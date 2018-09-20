package com.pranay.kotlinretrofitapicall.api.service

import com.pranay.kotlinretrofitapicall.api.response.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import test.kotlin.nuhin13.kotlintest.api.response.PreferTime

/**
 * Created by Pranay on 7/15/2017.
 */
interface NewsService {
    @GET("articles")
    fun getNewsApi(@Query("source") source: String,
                   @Query("sortBy") sortby: String,
                   @Query("apiKey") apiKey: String): Observable<NewsListResponse>

   @GET("v2/times")
   fun getValidTime(@Query("for") source: String): Observable<PreferTime>


}