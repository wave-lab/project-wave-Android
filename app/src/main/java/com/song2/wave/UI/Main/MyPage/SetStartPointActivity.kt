package com.song2.wave.UI.Main.MyPage

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_set_start_point.*
import org.jetbrains.anko.startActivity
import java.util.*

class SetStartPointActivity : AppCompatActivity() {

    lateinit var playTime: String
    lateinit var seekbar: SeekBar

    var playbackPosition = 0
    var currentPosition = 0

    var isStart = false
    var isComplete = false
    var isPlaying = false

    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_start_point)

        var upload_song_uri: String = intent.getStringExtra("musicDataURI")

        initialSetting(upload_song_uri)

        iv_set_start_act_play_btn.setOnClickListener {
            Log.e("seekbarSelectorBefore", sb_set_start_act_seekbar.isSelected.toString())

            if (iv_set_start_act_play_btn.isSelected == false && isPlaying == false)
                playAudio()
            else if (!iv_set_start_act_play_btn.isSelected)
                restart()
            else
                pauseAudio()

            iv_set_start_act_play_btn.isSelected = !iv_set_start_act_play_btn.isSelected
            Log.e("seekbarSelector", sb_set_start_act_seekbar.isSelected.toString())
        }

        iv_set_start_act_upload_btn.setOnClickListener {
            try {
                startActivity<UpLoadSongCoverActivity>("StartPoint" to currentPosition.toString(), "songURI" to upload_song_uri)
                finish()
                //killMediaPlayer()
                Log.e("startPoint", currentPosition.toString())
            } catch (e: Exception) {

            }
        }
    }

    fun addTimer() {
        val tt = object : TimerTask() {

            override fun run() {
                currentPosition = mediaPlayer.getCurrentPosition()
                runOnUiThread {
                    playTime = String.format(
                        "%02d:%02d", ((currentPosition / 1000) % 3600 / 60),
                        ((currentPosition / 1000) % 3600 % 60)
                    )
                    tv_set_start_act_duration_time.setText(playTime)
                    seekbar.setProgress(currentPosition)
                }
            }
        }
        val timer = Timer()
        timer.schedule(tt, 0, 1000)
    }

    fun initialSetting(upload_song_uri : String){

        mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(upload_song_uri))
        mediaPlayer.prepare()

        addSeekBar()
        addTimer()

        var play_duration = mediaPlayer.getDuration()
        var lengthOfSong =
            String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))
        tv_set_start_act_length_of_song.setText(lengthOfSong)

        seekbar.setMax(play_duration)
    }

    //seekbar touchListener
    fun addSeekBar() {
        seekbar = sb_set_start_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true
                playbackPosition = seekbar.progress
                mediaPlayer.seekTo(playbackPosition)

                Log.e("onStopTrackingTouch", sb_set_start_act_seekbar.isSelected.toString())
                if (!sb_set_start_act_seekbar.isSelected) {
                    mediaPlayer.start()
                } else
                    mediaPlayer.pause()
            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                Log.e("onStartTrackingTouch", sb_set_start_act_seekbar.isSelected.toString())

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
    }

    //미디어를 재생 메소드
    fun playAudio() {

        try {
            mediaPlayer.start()
            isPlaying = true
        } catch (e: Exception) {
            Log.e("first_start", e.toString())
            finish()
        }

    }

    fun pauseAudio() {
        try {
            mediaPlayer.pause()
            isPlaying = false
        } catch (e: Exception) {
            Log.e("pause", e.toString())
            finish()
        }
    }

    fun restart() {
        try {
            mediaPlayer.start()
            isPlaying = true
        } catch (e: Exception) {
            Log.e("start", e.toString())
            finish()
        }
    }

    fun killMediaPlayer() {
        mediaPlayer.release()
    }


}