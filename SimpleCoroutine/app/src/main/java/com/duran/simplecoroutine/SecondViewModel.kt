package com.duran.simplecoroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ViewModel이 필요가 없을때 CoroutineScope를 중지시켜 주는 것에 대한 공식 문서
// https://developer.android.com/topic/libraries/architecture/coroutines?hl=ko

class SecondViewModel : ViewModel() {
    /*
    a와 b의 차이점

    a는 viewModel이 필요가 없어진 시점에도 계속해서 Log가 찍히고 있지만
    b는 viewModel이 필요가 없어진 시점에 Log가 더이상 찍히지 않는다.
    */

    fun a() {

        CoroutineScope(Dispatchers.IO).launch {
            for(i in 0..10) {
                delay(1000)
                Log.d("SecondViewModel A : ", i.toString())
            }
        }

    }

    fun b(){
        viewModelScope.launch {
            for(i in 0..10) {
                delay(1000)
                Log.d("SecondViewModel B : ", i.toString())
            }
        }
    }


}