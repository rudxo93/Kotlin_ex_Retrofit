package com.duran.simplecoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
간단하게 코루틴과 / ViewModelScope

kotlin 코루틴에 대한 구글 공식 문서
https://developer.android.com/kotlin/coroutines?hl=ko

코루틴 한글 가이드 문서
https://myungpyo.medium.com/reading-coroutine-official-guide-thoroughly-part-0-20176d431e9d
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TEST", "START")

        // 코루틴 실행 -> 비동기 작업을 원하는 형태로 실행할 수 있다.
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TEST", "CoroutineScope START")
            // 비동기작업 실행
            a()
            b()
            Log.d("TEST", "CoroutineScope END")
        }

        Log.d("TEST", "END")
        // 예상순서 START -> END -> AP1 -> BP1 -> AP2 -> BP2
        // BUT START -> END -> AP1 -> AP2 -> BP1 -> BP2
    }

    suspend fun a() {
        // a작업은 오래걸려서 2초정도
        delay(1000)
        Log.d("TEST", "AP1")

        delay(1000)
        Log.d("TEST", "AP2")
    }

    suspend fun b() {
        // b작업은 오래걸려서 2초정도
        delay(1000)
        Log.d("TEST", "BP1")

        delay(1000)
        Log.d("TEST", "BP2")
    }
}