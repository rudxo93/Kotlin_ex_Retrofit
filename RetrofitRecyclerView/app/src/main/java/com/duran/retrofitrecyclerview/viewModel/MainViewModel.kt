package com.duran.retrofitrecyclerview.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duran.retrofitrecyclerview.api.MyApi
import com.duran.retrofitrecyclerview.api.RetrofitInstance
import com.duran.retrofitrecyclerview.model.Plant
import com.duran.retrofitrecyclerview.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // val retrofitInstance : MyApi = RetrofitInstance.getInstance().create(MyApi::class.java)

    private val repository = Repository()

    private val _result = MutableLiveData<List<Plant>>()
    val result : LiveData<List<Plant>>
        get() = _result

    fun getAllData() = viewModelScope.launch {
        Log.d("MainViewModel", repository.getAllData().toString())
        _result.value = repository.getAllData()
    }

}