package com.song2.wave.UI.Signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_signup_select_artist.*

class SignupSelectArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_select_artist)

        btn_signup_artist_next.setOnClickListener {
            var intent = Intent(applicationContext, SignupGenreActivity::class.java)
            startActivity(intent)
        }
    }
}
