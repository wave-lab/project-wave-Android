package com.song2.wave.UI.Main.MyPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.song2.wave.R
import com.song2.wave.UI.Signup.SignupGenreActivity
import kotlinx.android.synthetic.main.activity_upload_song_info.*
import org.jetbrains.anko.startActivity

class UploadSongInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_song_info)

        var start_point = intent.getStringExtra("StartPoint")
        var upload_song_uri =  intent.getStringExtra("songURI")
        var upload_pic_uri =  intent.getStringExtra("picURI")

        tv_title_confirm.setOnClickListener {
            rl_upload_song_info_origin_singer_edt.visibility = View.VISIBLE

            //가수
            rl_upload_song_info_origin_title.visibility = View.GONE
            rl_upload_song_info_origin_artist.visibility = View.VISIBLE
            rl_upload_song_info_comment.visibility = View.GONE
        }

        tv_singer_confirm.setOnClickListener{
            rl_upload_song_info_origin_comment_edt.visibility = View.VISIBLE

            //코멘트
            rl_upload_song_info_origin_title.visibility = View.GONE
            rl_upload_song_info_origin_artist.visibility = View.GONE
            rl_upload_song_info_comment.visibility = View.VISIBLE
        }


        iv_upload_song_info_act_confirm_btn.setOnClickListener {
            startActivity<UpLoadGenreActivity>(
                "Title" to et_upload_song_info_origin_title.text.toString(),
                "Artist" to et_upload_song_info_origin_singer.text.toString(),
                "Comment" to et_upload_song_info_comment.text.toString(),
                "StartPoint" to start_point.toString(),
                "songURI" to upload_song_uri,
                "picURI" to upload_pic_uri
            )
        }
    }
}