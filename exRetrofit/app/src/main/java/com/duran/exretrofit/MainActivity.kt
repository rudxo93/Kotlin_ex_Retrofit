package com.duran.exretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
Simple Retrofit Ex

api호출할 페이지(해당 주소를 통해서 데이터를 받아온다.)
https://jsonplaceholder.typicode.com/

https://jsonplaceholder.typicode.com/posts
https://jsonplaceholder.typicode.com/posts/3
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RetrofitInstance.getInstnce().create(MyApi::class.java)
        api.getPost1().enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("API1", response.toString())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("API1", "fail")
            }

        })

    }
}