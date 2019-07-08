package com.song2.wave.UI.Signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_signup_genre.*

class SignupGenreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_genre)

        btn_signup_genre_nex.setOnClickListener {
            var intent = Intent(applicationContext, SignupMoodActivity::class.java)
            startActivity(intent)
        }
    }
}
