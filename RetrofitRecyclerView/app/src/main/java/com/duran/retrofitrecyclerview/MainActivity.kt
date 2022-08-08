package com.duran.retrofitrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duran.retrofitrecyclerview.adapter.CustomAdapter
import com.duran.retrofitrecyclerview.viewModel.MainViewModel

// https://raw.githubusercontent.com/googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json

// 안드로이드 권장 앱 아키텍처에 관하여 공식 문서
// https://developer.android.com/training/dependency-injection/manual?hl=ko

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getAllData()

        val rv = findViewById<RecyclerView>(R.id.rv)

        viewModel.result.observe(this, Observer {

            val customAdapter = CustomAdapter(this, it)
            rv.adapter = customAdapter
            rv.layoutManager = LinearLayoutManager(this)


        })

    }
}