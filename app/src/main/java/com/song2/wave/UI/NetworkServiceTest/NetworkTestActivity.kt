package com.song2.wave.UI.NetworkServiceTest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.song2.wave.R
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService


class NetworkTestActivity : AppCompatActivity() {
    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.networkservice_test)

        val getEmailCheckResponse = networkService.getEmailCheckResponse("bghgu@naver.com")

    }


}

