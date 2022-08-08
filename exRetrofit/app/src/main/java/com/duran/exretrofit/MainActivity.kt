package com.duran.exretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duran.exretrofit.adapter.CustomAdapter
import com.duran.exretrofit.model.Post
import com.duran.exretrofit.viewModel.MainViewModel

/*
Simple Retrofit Ex

api호출할 페이지(해당 주소를 통해서 데이터를 받아온다.)
https://jsonplaceholder.typicode.com/

https://jsonplaceholder.typicode.com/posts
https://jsonplaceholder.typicode.com/posts/3
*/

// 1 3 2 4  /  2 3 1 4 -> 현재 비동기적으로 실행되고 있어서 1 2 3 4 가 아닌 실행할때마다 무작위로 실행되었다.


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPost1()
        viewModel.getPostNumber(3)
        viewModel.getPostAll()

        val area1 = findViewById<TextView>(R.id.area1)
        val area2 = findViewById<TextView>(R.id.area2)

        viewModel.liveWord1.observe(this, Observer {
            area1.text = it.toString()
        })

        viewModel.liveWord2.observe(this, Observer {
            area2.text = it.toString()
        })

        val rv = findViewById<RecyclerView>(R.id.rv)

        viewModel.liveWordList.observe(this, Observer {
            val customAdapter = CustomAdapter(it as ArrayList<Post>)
            rv.adapter = customAdapter
            rv.layoutManager = LinearLayoutManager(this)
        })
    }
}