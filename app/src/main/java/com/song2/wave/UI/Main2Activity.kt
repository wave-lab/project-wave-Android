package com.song2.wave.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.song2.wave.R
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_test.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val hd = Handler()
        hd.postDelayed(splashhandler(), 1000) // 3000ms=3초후에 핸들러 실행 //딜레이 3000
    }

    private inner class splashhandler : Runnable {
        override fun run() {
            val translate = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_out_up)
            text_test.startAnimation(translate)
        }
    }

}