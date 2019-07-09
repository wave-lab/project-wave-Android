package com.song2.wave.UI.Signup

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_signup_genre.*

class SignupGenreActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var receivedImgUri : Uri
    lateinit var genreArr : Array<ImageView>

    override fun onClick(v: View?) {
        for(i in 0..7){
            if (v!!.id == genreArr[i].getId()) {
                Toast.makeText(applicationContext, "장르 " + v!!.id.toString() +"번 버튼 선택", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_genre)

        receivedImgUri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri

        var popImg : ImageView = findViewById(R.id.img_signup_genre_act_pop)
        var balladImg : ImageView = findViewById(R.id.img_signup_genre_act_ballad)
        var danceImg : ImageView = findViewById(R.id.img_signup_genre_act_dance)
        var aqusticImg : ImageView = findViewById(R.id.img_signup_genre_act_aqustic)
        var rockImg : ImageView = findViewById(R.id.img_signup_genre_act_rock)
        var soulImg : ImageView = findViewById(R.id.img_signup_genre_act_soul)
        var hiphopImg : ImageView = findViewById(R.id.img_signup_genre_act_hiphop)
        var eletronicImg : ImageView = findViewById(R.id.img_signup_genre_act_eletronic)

        for(i in 0..7){
            genreArr[i].setOnClickListener(this)
        }

        genreArr = arrayOf<ImageView>(popImg, balladImg, danceImg, aqusticImg, rockImg, soulImg, hiphopImg, eletronicImg)

        btn_signup_genre_nex.setOnClickListener {
            var intent = Intent(applicationContext, SignupMoodActivity::class.java)
            intent.putExtra("imageUri",receivedImgUri)
            startActivity(intent)
        }
    }
}
