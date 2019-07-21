package com.song2.wave.UI

import android.animation.Animator
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView
import com.airbnb.lottie.LottieAnimationView
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_like_animation.*

class LikeAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_animation)

        //--------------------------------------애니메이션---------
        // val video : VideoView = findViewById(R.id.video)
        //com.airbnb.lottie.LottieAnimationView 아이디
        val love : LottieAnimationView = findViewById(R.id.lottie_main_act_like)
        val videoView = findViewById<VideoView>(R.id.video)
        val path = "android.resource://" + packageName + "/" + R.raw.try_11
        videoView?.setVideoURI(Uri.parse(path))
        //val button = findViewById<Button>(R.id.button)

        lottie_main_act_like.setOnClickListener {
            love.playAnimation()
            val isPlaying = videoView.isPlaying
            if (isPlaying) {
                //videoView.pause()
            } else {
                videoView.start()
            }
        }
        love.addAnimatorListener(object : Animator. AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.e("Animation:","repeat")
            }
            override fun onAnimationEnd(animation: Animator?) {
                //Toast.makeText( application , "끝~",Toast. LENGTH_SHORT ).show()
            }
            override fun onAnimationCancel(animation: Animator?) {
                Log.e("Animation:","cancel")  //취소
            }
            override fun onAnimationStart(animation: Animator?) {
                Log.e("Animation ","star") //시작
            }
        })
        //------------------------------------------------애니메이션

    }
}
