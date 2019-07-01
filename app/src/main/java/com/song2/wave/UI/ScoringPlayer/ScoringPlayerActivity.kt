package com.song2.wave.Util.Kakao.service

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_scoring_player.*

class ScoringPlayerActivity : Activity() {

    var mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoring_player)

        var start = btn_scoring_player_play
        var pause = btn_scoring_player_pause
        var restart = btn_scoring_player_restart

        start.setOnClickListener {

/*            if (mediaPlayer != null) {
                mediaPlayer.stop()
                mediaPlayer = null!!
            }*/
            playAudio()
        }

        pause.setOnClickListener{}
        restart.setOnClickListener{}
    }



    //미디어를 재생하는 사용자 정의 메소드
    fun playAudio() {

        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource("https://github.com/nzkozar/ExoplayerExample/blob/master/sample.m4a?raw=true")

        mediaPlayer.prepare()
        mediaPlayer.start()
    }


    //액티비티가 화면에서 제거될 때 호출되는 메서드
    override fun onDestroy() {
        killMediaPlayer()
        super.onDestroy()
    }

    fun killMediaPlayer() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.release()
        }
    }
}