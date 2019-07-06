package com.song2.wave.UI.MainPlayer

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.SeekBar
import com.song2.wave.R
import com.song2.wave.Util.Player.Service.MusicService
import kotlinx.android.synthetic.main.activity_main_player.*
import java.util.*

class MainPlayerActivity : AppCompatActivity() {

    lateinit var musicService : MusicService

    lateinit var playTime: String
    lateinit var mediaPlayer: MediaPlayer
    lateinit var seekbar: SeekBar

    var isPlaying = false
    var playbackPosition = 0
    var currentPosition = 0

    // var n_sbHandler = sbHandler()
    // var seekBarThread = sbThread()

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
    fun addTimer(){
        val tt = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    playTime = String.format(
                        "%02d:%02d",
                        ((mediaPlayer.getCurrentPosition() / 1000) % 3600 / 60),
                        ((mediaPlayer.getCurrentPosition() / 1000) % 3600 % 60)
                    )

                    tv_main_player_duration_time.setText(playTime)
                   // seekbar.setProgress(mediaPlayer.getCurrentPosition())
                }
            }
        }

        /////////// / Timer 생성 //////////////
        val timer = Timer()
        timer.schedule(tt, 0, 1000)
        //////////////////////////////////////

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_player)

        mediaPlayer = MediaPlayer()
       // val intent = Intent(this@MainPlayerActivity, MusicService::class.java)
        //startService(intent)
        //addSeekBar()
        playerBtn()
       // addTimer()

        iv_main_player_like_btn.setOnClickListener {
            iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //musicThread.killMediaPlayer()
        killMediaPlayer()
    }

    fun addSeekBar() {
        seekbar = sb_scoring_player_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true

                playbackPosition = seekbar.progress
                mediaPlayer.seekTo(playbackPosition)

                //if 재생 중이였다면
/*                if(iv_main_player_act_stop_btn.isSelected){
                    mediaPlayer.start()
                    seekBarThread.run()
                }*/

                //잠시 지움 - mediaPlayer.start()

            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                isPlaying = false
                mediaPlayer.pause()
                //error : mediaplayer에 아 처음에 아무것도 만지지 않은 상태에서 seekbar 건들경우 error
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

    fun playerBtn() {

        //if Looping == False
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(p0: MediaPlayer?) {
                Log.v("Complete", "Complete")
                //musicThread.nextSong()
                //nextSong()
            }
        })



        iv_main_player_act_stop_btn.setOnClickListener {
            Log.v("TAG","테스트0")
            if (iv_main_player_act_stop_btn.isSelected and (mediaPlayer.getCurrentPosition() == 0)) {
                Log.v("TAG","테스트1")
                try {
/*                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer = null
                }*/
                    Log.v("TAG","startService")
                    //musicThread.playAudio(sourceMusicArray[currentPosition])
                   // val intent = Intent(this@MainPlayerActivity, MusicService::class.java)
                    //startService(intent)


                    Log.v("Asdf","테스트6")
                    playAudio(sourceMusicArray[currentPosition])

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("ERROR", mediaPlayer.toString())
                }

            } else if (iv_main_player_act_stop_btn.isSelected) {
                //musicThread.pauseAudio()
                Log.v("TAG","테스트1")
                pauseAudio()

            } else {
                //musicThread.restart()
                Log.v("TAG","테스트2")
                restart()

            }
            iv_main_player_act_stop_btn.isSelected = !iv_main_player_act_stop_btn.isSelected

        }

    }


    //미디어를 재생하는 사용자 정의 메소드
    fun playAudio(url: String) {

        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()

        var play_duration = mediaPlayer!!.getDuration()
        var lenthOfSong =
            String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))

        tv_main_player_length_of_song.setText(lenthOfSong)

        isPlaying = true

//        seekbar.setMax(play_duration)
        //seekBarThread.start()
    }



    fun prevSong() {

        if (currentPosition > 0) {
            mediaPlayer.reset()
            currentPosition -= 1
           // playAudio(sourceMusicArray[currentPosition])

        } else {
            killMediaPlayer()
            //mediaPlayer.release()
        }

    }

    /*
    fun nextSong() {

        if (currentPosition < sourceMusicArray.size) {
            mediaPlayer.reset()
            currentPosition += 1
            Log.v("TAG","테스트4")
            playAudio(sourceMusicArray[currentPosition])

        } else {
            killMediaPlayer()
        }

    }
    */

    fun pauseAudio() {

        isPlaying = false

        playbackPosition = mediaPlayer.getCurrentPosition()
        mediaPlayer!!.pause()

    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경

         val intent = Intent(this@MainPlayerActivity, MusicService::class.java)
        startService(intent)

        /*
        mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        mediaPlayer.start() // 시작
*/
        // seekBarThread.run()
    }

    fun killMediaPlayer() {
/*      if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*/
        Log.v("TAG","테스트3")
        mediaPlayer!!.release()
    }
}
