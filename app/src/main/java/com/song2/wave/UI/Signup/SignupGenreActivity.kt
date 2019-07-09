package com.song2.wave.UI.Signup

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_signup_genre.*

class SignupGenreActivity : AppCompatActivity() {

    lateinit var receivedImgUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_genre)

        receivedImgUri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri

        Log.v("asdf","이미지 = " + receivedImgUri)

        btn_signup_genre_nex.setOnClickListener {
            var intent = Intent(applicationContext, SignupMoodActivity::class.java)
            startActivity(intent)
        }
    }
}
