package com.song2.wave.Util.Kakao.service

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.SeekBar
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_scoring_player.*
import java.lang.Exception

class ScoringPlayerActivity : Activity() {
    lateinit var playTime: String
    lateinit var mediaPlayer: MediaPlayer
    lateinit var seekbar: SeekBar

    var isPlaying = false
    var playbackPosition = 0
    var currentPosition = 0

    var n_sbHandler = sbHandler()
    var musicThread = playThread()
    var seekBarThread = sbThread()

    var sourceMusicArray: Array<String> = arrayOf(
        "https://project-wave-1.s3.ap-northeast-2.amazonaws.com/Roller+Coaster_%EC%B2%AD%ED%95%98_320k.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-01.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-02.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-03.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-04.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-05.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-06.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-07.mp3",
        "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-08.mp3"
    )

    inner class playThread : Thread() {

        //미디어를 재생하는 사용자 정의 메소드
        fun playAudio(url: String) {

            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()

            var play_duration = mediaPlayer!!.getDuration()
            var lenthOfSong =
                String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))

            tv_scoring_player_length_of_song.setText(lenthOfSong)

            isPlaying = true

            seekbar.setMax(play_duration)
            seekBarThread.start()
        }


        fun prevSong() {

            if (currentPosition > 0) {
                mediaPlayer.reset()
                currentPosition -= 1
                playAudio(sourceMusicArray[currentPosition])

            } else {
                killMediaPlayer()
                //mediaPlayer.release()
            }

        }

        fun nextSong() {

            if (currentPosition < sourceMusicArray.size) {
                mediaPlayer.reset()
                currentPosition += 1
                playAudio(sourceMusicArray[currentPosition])

            } else {
                killMediaPlayer()
            }

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

            seekBarThread.start()
        }

        fun killMediaPlayer() {
/*      if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*/
            mediaPlayer!!.release()
        }

    }

    inner class sbThread : Thread() {
        override fun run() {
            while (isPlaying) {
                seekbar.setProgress(mediaPlayer.getCurrentPosition())
                n_sbHandler.sendEmptyMessageDelayed(0, 1000)
            }
        }
    }

    inner class sbHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg!!.what == 0) {
                playTime = String.format(
                    "%02d:%02d",
                    ((mediaPlayer.getCurrentPosition() / 1000) % 3600 / 60),
                    ((mediaPlayer.getCurrentPosition() / 1000) % 3600 % 60)
                )

                tv_scoring_player_duration_time.setText(playTime)
                /*
                Log.v("handlerError",(mediaPlayer.getCurrentPosition()/1000).toString())
                Log.v("handlerError",(mediaPlayer.getDuration()/1000).toString())*/
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoring_player)
        mediaPlayer = MediaPlayer()

        addSeekBar()
        playerBtn()

        iv_scoring_player_act_like.setOnClickListener {
            iv_scoring_player_act_like.isSelected = !iv_scoring_player_act_like.isSelected
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        musicThread.killMediaPlayer()
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

                seekBarThread.start()
            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                isPlaying = false
                mediaPlayer.pause()
                //error : mediaplayer에 아 처음에 아무것도 만지지 않은 상태에서 seekbar 건들경우 error
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

        //if Looping == False
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(p0: MediaPlayer?) {
                Log.v("Complete", "Complete")
                musicThread.nextSong()
            }
        })

        btn_scoring_player_prev.setOnClickListener {
            musicThread.prevSong()
        }

        btn_scoring_player_play.setOnClickListener {

            try {
/*                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer = null
                }*/

                musicThread.playAudio(sourceMusicArray[currentPosition])

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ERROR", mediaPlayer.toString())
            }
        }

        btn_scoring_player_stop.setOnClickListener {
            musicThread.stopAudio()
        }

        btn_scoring_player_pause.setOnClickListener {
            musicThread.pauseAudio()
        }

        btn_scoring_player_restart.setOnClickListener {
            musicThread.restart()
        }

        btn_scoring_player_next.setOnClickListener {
            musicThread.nextSong()
        }
    }

}