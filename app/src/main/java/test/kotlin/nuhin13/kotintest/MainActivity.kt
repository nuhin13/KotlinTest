package test.kotlin.nuhin13.kotintest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener(View.OnClickListener {
            getDataFromView();
        })
    }

    private fun apiCall() {
        val apiService = ApiService.create()
        apiService.getOrderInfo(1308, 77365,
                "vE3AaxzeohKq6aRVgxOXPMQYpfJ0WFqKRsdG8c3cxNDaA8HXGOsR2La50PqU", "ongoing")
    }

    private fun fetchData() {
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.searchUsers("Lagos", "Java")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                  //  result ->
                    Log.d("Result", "There are  Java developers in Lagos")
                }, { //error ->
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

    private fun goToNextActivity(email: String){
        val intent = Intent(this@MainActivity,Main2Activity::class.java)
        intent.putExtra("Username", email);
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
