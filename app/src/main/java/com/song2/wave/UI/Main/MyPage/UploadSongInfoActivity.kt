package com.song2.wave.UI.Main.MyPage

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_upload_song_info.*

class UploadSongInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_song_info)

        tv_title_confirm.setOnClickListener {
            rl_upload_song_info_origin_singer_edt.visibility = View.VISIBLE

            //가수
            rl_upload_song_info_origin_title.visibility = View.GONE
            rl_upload_song_info_origin_artist.visibility = View.VISIBLE
            rl_upload_song_info_comment.visibility = View.GONE
        }

        tv_singer_confirm.setOnClickListener{
            rl_upload_song_info_origin_comment_edt.visibility = View.VISIBLE

            //가수
            rl_upload_song_info_origin_title.visibility = View.GONE
            rl_upload_song_info_origin_artist.visibility = View.GONE
            rl_upload_song_info_comment.visibility = View.VISIBLE
        }


        iv_upload_song_info_act_confirm_btn.setOnClickListener {
            //통신
        }

    }
}
