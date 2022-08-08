package com.duran.retrofitrecyclerview.repository

import com.duran.retrofitrecyclerview.api.MyApi
import com.duran.retrofitrecyclerview.api.RetrofitInstance

class Repository {

    private val client = RetrofitInstance.getInstance().create(MyApi::class.java)

    suspend fun getAllData() = client.getAllPlants()

}