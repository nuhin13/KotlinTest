package test.kotlin.nuhin13.kotlintest

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result


class SearchRepository(val apiService: ApiService) {

    fun getPreferTime(): Observable<PreferTime> {
        return apiService.getPreferTime()
    }

}