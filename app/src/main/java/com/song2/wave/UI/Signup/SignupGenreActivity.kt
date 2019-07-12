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
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity

class SignupGenreActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var receivedImgUri : Uri
    lateinit var genreArr : Array<ImageView>
    lateinit var selectedGenreArr : ArrayList<String>
    lateinit var selectedArtistArr : ArrayList<String>


    override fun onClick(v: View?) {
        for (i in 0..7) {
            if (v!!.id == genreArr[i].getId()) {

                selectedGenreArr.add("g" + ((i+1).toString()))
                Toast.makeText(applicationContext, "장르 " +"m" + (i+1).toString() + "번 버튼 선택", Toast.LENGTH_LONG).show()

            }
        }
        for (i in 0..selectedGenreArr.size - 1)
            Log.v("act", "장르 배열 값 = " + selectedGenreArr[i])

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_genre)

        if(!intent.getStringExtra("Confirm").equals("upload")){
            receivedImgUri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri
        }

        selectedGenreArr = ArrayList<String>()
        selectedArtistArr = intent.getStringArrayListExtra("selectedArtistArr")

        var popImg: ImageView = findViewById(R.id.img_signup_genre_act_pop)
        var balladImg: ImageView = findViewById(R.id.img_signup_genre_act_ballad)
        var danceImg: ImageView = findViewById(R.id.img_signup_genre_act_dance)
        var aqusticImg: ImageView = findViewById(R.id.img_signup_genre_act_aqustic)
        var rockImg: ImageView = findViewById(R.id.img_signup_genre_act_rock)
        var soulImg: ImageView = findViewById(R.id.img_signup_genre_act_soul)
        var hiphopImg: ImageView = findViewById(R.id.img_signup_genre_act_hiphop)
        var eletronicImg: ImageView = findViewById(R.id.img_signup_genre_act_eletronic)


        genreArr =
                arrayOf<ImageView>(balladImg, popImg, aqusticImg, danceImg, hiphopImg, soulImg, rockImg, eletronicImg)
        for (i in 0..7) {
            genreArr[i].setOnClickListener(this)
        }

        btn_signup_genre_nex.setOnClickListener {

            var intent = Intent(applicationContext, SignupMoodActivity::class.java)
            intent.putExtra("imageUri",receivedImgUri)
            intent.putExtra("selectedArtistArr", selectedArtistArr)
            intent.putExtra("selectedGenreArr",selectedGenreArr)
            startActivity(intent)

            if (intent.getStringExtra("Confirm").equals("upload")) {
                //음악 Upload
                Log.e("picURI:::::::::::::::::::",intent.getStringExtra("picURI"))
                startActivity<SignupMoodActivity>(
                    "Confirm" to "upload",
                    "Title" to intent.getStringExtra("StartPoint").toString(),
                    "Artist" to intent.getStringExtra("Artist").toString(),
                    "Comment" to intent.getStringExtra("Comment").toString(),
                    "StartPoint" to intent.getStringExtra("StartPoint").toString(),
                    "songURI" to intent.getStringExtra("songURI").toString(),
                    "picURI" to intent.getStringExtra("picURI").toString(),
                    "genre" to genre)
            } else {
                //회원가입
                var intent = Intent(applicationContext, SignupMoodActivity::class.java)
                intent.putExtra("imageUri", receivedImgUri)
                intent.putExtra("genreList", genre)
                intent.putExtra("Confirm","signup")
                startActivity(intent)
            }

        }
    }
}
