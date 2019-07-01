package com.song2.wave.Util.Kakao.service

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_scoring_player.*
import java.lang.Exception

class ScoringPlayerActivity : Activity() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var seekbar: SeekBar
    var isPlaying = false
    var playbackPosition = 0

    inner class sbThread : Thread() {
        override fun run() {
            while (isPlaying) {
                seekbar.setProgress(mediaPlayer.getCurrentPosition())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoring_player)

        addSeekBar()
        playerBtn()
    }

    fun addSeekBar() {

        seekbar = sb_scoring_player_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true

                val p_time = seekbar.progress
                mediaPlayer.seekTo(p_time)
                mediaPlayer.start()

                sbThread().start()

            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                isPlaying = false
                mediaPlayer.pause()
                //error : lateinit을 설정 해 놔서, 처음에 아무것도 만지지 않은 상태에서 seekbar 건들면
            }


            //seek바의 값이 변경되었을떄 + fromUser: Boolean : 터치를 통해 변경했으면 false , 코드를 통하면 true
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (seekbar!!.getMax() == progress) {
                    isPlaying = false
                    mediaPlayer.stop()
                }
            }
        })
    }

    fun playerBtn() {
        btn_scoring_player_play.setOnClickListener {

            try {
/*                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer = null
                }*/
                playAudio()

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ERROR", mediaPlayer.toString())
            }
        }

        btn_scoring_player_stop.setOnClickListener {
            stopAudio()
        }

        btn_scoring_player_pause.setOnClickListener {
            pauseAudio()
        }

        btn_scoring_player_restart.setOnClickListener {
            restart()
        }
    }

    //미디어를 재생하는 사용자 정의 메소드
    fun playAudio() {
        var url = "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-08.mp3"

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(url)

        mediaPlayer!!.prepare()
        mediaPlayer!!.start()

        var play_duration = mediaPlayer!!.getDuration()
        isPlaying = true

        seekbar.setMax(play_duration)
        sbThread().start()
    }

    fun stopAudio() {
        isPlaying = false

        mediaPlayer.stop()
        seekbar.setProgress(0)
        killMediaPlayer()

    }

    fun pauseAudio() {

        isPlaying = false

        playbackPosition = mediaPlayer.getCurrentPosition()
        mediaPlayer!!.pause()

    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경

        mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        mediaPlayer.start() // 시작


        sbThread().start()
    }

    //액티비티가 화면에서 제거될 때 호출되는 메서드
    override fun onDestroy() {
        super.onDestroy()
        killMediaPlayer()
    }

    fun killMediaPlayer() {
/*        if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*/

        mediaPlayer!!.release()
    }
}