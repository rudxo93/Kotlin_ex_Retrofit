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

// 1 3 2 4  /  2 3 1 4 -> 현재 비동기적으로 실행되고 있어서 1 2 3 4 가 아닌 실행할때마다 무작위로 실행되었다.


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RetrofitInstance.getInstnce().create(MyApi::class.java)

        // 1. 유저 정보를 가져와서(userId)
        api.getPost1().enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("API1", response.body().toString())
                // 2. userId 기반으로 데이터를 가져오고 가져온 닉네임 값
                api.getPostNumber(2).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        Log.e("API2", response.body().toString())
                        // 3. 닉네임 기반으로 데이터를 가져오고 유저의 댓글을 가져와서
                        api.getPostNumber(3).enqueue(object : Callback<Post> {
                            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                                Log.e("API3", response.body().toString())
                                // 4. 유저의 댓글 기반으로 대댓글
                                api.getPostNumber(4).enqueue(object : Callback<Post> {
                                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                                        Log.e("API4", response.body().toString())
                                    }

                                    override fun onFailure(call: Call<Post>, t: Throwable) {
                                        Log.e("API4", "fail")
                                    }

                                })
                            }

                            override fun onFailure(call: Call<Post>, t: Throwable) {
                                Log.e("API3", "fail")
                            }

                        })
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("API2", "fail")
                    }

                })
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("API1", "fail")
            }

        })

    }
}