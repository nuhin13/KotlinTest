package test.kotlin.nuhin13.kotlintest

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pranay.kotlinretrofitapicall.api.ApiProduction
import com.pranay.kotlinretrofitapicall.api.response.NewsListResponse
import com.pranay.kotlinretrofitapicall.api.service.NewsService
import com.pranay.kotlinretrofitapicall.rx.RxAPICallHelper
import com.pranay.kotlinretrofitapicall.rx.RxAPICallback
import com.pranay.kotlinroomdbtodo.adapter.PreferTimeAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import test.kotlin.nuhin13.kotlintest.api.response.PreferTime
import test.kotlin.nuhin13.kotlintest.api.response.Time

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var mNewsService: NewsService? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNewsService = ApiProduction(this).provideService(NewsService::class.java)
        apiCall()

        buttonLogin.setOnClickListener(View.OnClickListener {
            getDataFromView();
        })
    }

    private fun getNewsList() {
        //Create retrofit Service
        var mNewsService: NewsService = ApiProduction(this).provideService(NewsService::class.java)
        //List of source : https://newsapi.org/sources
        //List of sort by option: https://newsapi.org/#apiArticles
        var apiCall: Observable<NewsListResponse> = mNewsService.getNewsApi("techcrunch", "top",
                "3c08e0a94cfe43e69f0386f05eb3177f") //Test API Key

        RxAPICallHelper().call(apiCall, object : RxAPICallback<NewsListResponse> {
            override fun onSuccess(newsItems: NewsListResponse) {
                //status= "error" in case of error
                if (newsItems.getStatus().equals("ok")) {
                    Log.e("Image", newsItems.getArticles()?.get(0)?.urlToImage)
                            //setNewsData(newsItems)
                }
            }

            override fun onFailed(throwable: Throwable) {
                Log.e("error", throwable.toString())
            }
        })
    }

    private fun apiCall() {
        var apiCall: Observable<PreferTime> = mNewsService!!.getValidTime("app")

        RxAPICallHelper().call(apiCall, object : RxAPICallback<PreferTime> {
            override fun onSuccess(newsItems: PreferTime) {
                Log.e("Image", newsItems.toString())
                showPreferTimeData(newsItems.times as List<Time>)
            }

            override fun onFailed(throwable: Throwable) {
                Log.e("error", throwable.toString())
            }
        })
    }

    private fun apiCallPreferTime() {
        val apiService = ApiService.create()
        apiService.getPreferTime()
    }

    private fun fetchData() {
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.getPreferTime()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                  //  result ->
                    Log.d("Result", "There are  Java developers in Lagos")
                }, { //error ->
                    Log.d("Error", "error")
                   // error.printStackTrace()
                })
    }

    private fun getDataFromView() {

        var email = "";
        var password = "";

        if (editEmail.text.toString().isNotEmpty() && editEmail.text.toString().isNotBlank()) {
            email = editEmail.text.toString();
        } else {
            Toast.makeText(this, "Email field empty", Toast.LENGTH_LONG).show();
        }

        if (editPassword.text.toString().isNotEmpty() && editPassword.text.toString().isNotBlank()) {
            password = editPassword.text.toString();
        } else {
            Toast.makeText(this, "password field empty", Toast.LENGTH_LONG).show();
        }

        if (!email.isEmpty() && !password.isEmpty()) {
            Toast.makeText(this, email + "\n" + password, Toast.LENGTH_LONG).show();
            goToNextActivity(email);
        } else {
            return;
        }
    }

    private fun showPreferTimeData(time: List<Time>){
            rv_prefer_time.layoutManager = GridLayoutManager(this,2)

            val preferTimeAdapter: PreferTimeAdapter = PreferTimeAdapter()
            preferTimeAdapter.setData(time)
            rv_prefer_time.adapter = preferTimeAdapter
    }

    private fun goToNextActivity(email: String){
        val intent = Intent(this@MainActivity,Main2Activity::class.java)
        intent.putExtra("Username", email);
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
