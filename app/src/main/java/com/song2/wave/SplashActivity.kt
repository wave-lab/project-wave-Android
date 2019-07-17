package com.song2.wave

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val hd = Handler()
        hd.postDelayed(splashhandler(), 1500) // 3000ms=3초후에 핸들러 실행 //딜레이 3000
    }

    private inner class splashhandler : Runnable {
        override fun run() {
            var intent = Intent(applicationContext, StartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out) // fade in, fade out 애니메이션 효과
            this@SplashActivity.finish() // 스플래쉬 페이지 액티비티 스택에서 제거
        }
    }
}