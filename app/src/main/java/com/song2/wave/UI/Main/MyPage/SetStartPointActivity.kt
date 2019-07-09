package com.song2.wave.UI.Main.MyPage

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_set_start_point.*
import java.util.*

class SetStartPointActivity : AppCompatActivity() {
/*
    lateinit var playTime: String
    lateinit var seekbar: SeekBar

    var playbackPosition = 0
    var currentPosition = 0


    var isPlaying = false*/

    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_start_point)

        var upload_song_uri: String = intent.getStringExtra("musicDataURI")
        mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(upload_song_uri))
        //mediaPlayer.setDataSource(this, Uri.parse(upload_song_uri))

        mediaPlayer.prepare()
        mediaPlayer.start()


        //addSeekBar()
        //setSeekbarUI()

    }

/*    fun setSeekbarUI(){
        fun addTimer(){
            val tt = object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        playTime = String.format(
                            "%02d:%02d", ((mediaPlayer.getCurrentPosition() / 1000) % 3600 / 60),
                            ((mediaPlayer.getCurrentPosition() / 1000) % 3600 % 60))

                        tv_set_start_act_duration_time.setText(playTime)
                        seekbar.setProgress(mediaPlayer.getCurrentPosition())
                    }
                }
            }

            /////////// / Timer 생성 //////////////
            val timer = Timer()
            timer.schedule(tt, 0, 1000)
            //////////////////////////////////////

        }
    }*/
/*
    //seekbar touchListener
    fun addSeekBar() {
        seekbar = sb_set_start_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true

                playbackPosition = seekbar.progress
                mediaPlayer.seekTo(playbackPosition)

                if(sb_set_start_act_seekbar.isSelected){
                    mediaPlayer.start()
                }
            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                isPlaying = false
                mediaPlayer.pause()
            }

            //seek바의 값이 변경되었을때 + fromUser: Boolean : 터치를 통해 변경했으면 false , 코드를 통하면 true
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekbar!!.getMax() == progress) {
                    isPlaying = false
                    mediaPlayer.stop()
                }
            }
        })
    }*/
/*

    //미디어를 재생하는 사용자 정의 메소드
    fun playAudio(url: String) {

        var play_duration = mediaPlayer.getDuration()
        var lengthOfSong =
            String.format("%02d:%02d", ((play_duration/ 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))

        tv_set_start_act_length_of_song.setText(lengthOfSong)

        isPlaying = true

        seekbar.setMax(play_duration)
        //seekBarThread.start()
    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경

        mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        mediaPlayer.start() // 시작

        //seekBarThread.run()
    }

    fun killMediaPlayer() {
*/
/*      if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*//*

        mediaPlayer!!.release()
    }
*/


}
