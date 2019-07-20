package com.song2.wave.UI.Main.MyPage.UpLoadSong

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_upload_genre.*
import org.jetbrains.anko.startActivity

class UpLoadGenreActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var genreArr : Array<ImageView>
    var selectedGenreArr = ArrayList<String>()

    override fun onClick(v: View?) {
        for(i in 0..7){
            if (v!!.id == genreArr[i].getId()) {
                selectedGenreArr.add("g" + ((i+1).toString()))
                Toast.makeText(applicationContext, "장르 " +"g" + (i+1).toString() + "번 버튼 선택", Toast.LENGTH_LONG).show()
            }
        }
        for(i in selectedGenreArr.indices)
            Log.v("act", "장르 배열 값 = " + selectedGenreArr[i])

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_genre)

        var popImg : ImageView = findViewById(R.id.img_upload_genre_act_pop)
        var balladImg : ImageView = findViewById(R.id.img_upload_genre_act_ballad)
        var danceImg : ImageView = findViewById(R.id.img_upload_genre_act_dance)
        var aqusticImg : ImageView = findViewById(R.id.img_upload_genre_act_aqustic)
        var rockImg : ImageView = findViewById(R.id.img_upload_genre_act_rock)
        var soulImg : ImageView = findViewById(R.id.img_upload_genre_act_soul)
        var hiphopImg : ImageView = findViewById(R.id.img_upload_genre_act_hiphop)
        var eletronicImg : ImageView = findViewById(R.id.img_upload_genre_act_eletronic)


        genreArr = arrayOf<ImageView>(balladImg, popImg, aqusticImg, danceImg, hiphopImg, soulImg, rockImg, eletronicImg)
        for(i in 0..7){
            genreArr[i].setOnClickListener(this)
        }

        btn_upload_genre_nex.setOnClickListener {
            Log.e("next btn_song",intent.getStringExtra("songURI").toString())
            Log.e("next btn_pic",intent.getStringExtra("picURI").toString())
            startActivity<UpLoadMoodActivity>(
                "Title" to intent.getStringExtra("StartPoint").toString(),
                "Artist" to intent.getStringExtra("Artist").toString(),
                "Comment" to intent.getStringExtra("Comment").toString(),
                "StartPoint" to intent.getStringExtra("StartPoint").toString(),
                "songURI" to intent.getStringExtra("songURI").toString(),
                "picURI" to intent.getStringExtra("picURI").toString(),
                "genre" to selectedGenreArr
            )
        }
    }
}
