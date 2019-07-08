package com.song2.wave.UI.MainPlayer

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.SeekBar
import com.song2.wave.AudioTest.AudioApplication
import com.song2.wave.R
import com.song2.wave.Util.Player.Service.MyForeGroundService
import kotlinx.android.synthetic.main.activity_main_player.*
import java.util.*

class MainPlayerActivity : AppCompatActivity() {

    lateinit var playTime: String
    lateinit var seekbar: SeekBar
    lateinit var myService : MyForeGroundService
    var selectedFlag : Int = 0

    var isPlaying = false
    var playbackPosition = 0
    var currentPosition = 0

    var mPosition : Int = 0

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


    /*
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

                tv_main_player_duration_time.setText(playTime)
                Log.v("handlerError",(mediaPlayer.getCurrentPosition()/1000).toString())
                //Log.v("handlerError",(mediaPlayer.getDuration()/1000).toString())
            }
        }
    } */

    /*
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
                    seekbar.setProgress(mediaPlayer.getCurrentPosition())
                }
            }
        }

        /////////// / Timer 생성 //////////////
        val timer = Timer()
        timer.schedule(tt, 0, 1000)
        //////////////////////////////////////

    }
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_player)

        myService = MyForeGroundService()
        var selectedTitle: String
        var selectedArtist : String

        mPosition = intent.getIntExtra("mPosition", 0)

        //AudioApplication.getInstance().serviceInterface.setPlayList(getAudioIds()) // 재생목록등록
        AudioApplication.getInstance().serviceInterface.play(mPosition) // 선택한 오디오재생

        addSeekBar()
        playerBtn()
       //addTimer()

        iv_main_player_like_btn.setOnClickListener {
            iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //musicThread.killMediaPlayer()
    }

    fun addSeekBar() {
        seekbar = sb_scoring_player_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true

                playbackPosition = seekbar.progress
             //   mediaPlayer.seekTo(playbackPosition)

                //if 재생 중이였다면
/*                if(iv_main_player_act_stop_btn.isSelected){
                    mediaPlayer.start()
                    seekBarThread.run()
                }*/


            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                isPlaying = false
                //mediaPlayer.pause()
                //error : mediaplayer에 아 처음에 아무것도 만지지 않은 상태에서 seekbar 건들경우 error
            }

            //seek바의 값이 변경되었을때 + fromUser: Boolean : 터치를 통해 변경했으면 false , 코드를 통하면 true
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (seekbar!!.getMax() == progress) {
                    isPlaying = false
                    //mediaPlayer.stop()
                }
            }
        })
    }

    fun playerBtn() {

        /*
        //if Looping == False
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(p0: MediaPlayer?) {
                Log.v("Complete", "Complete")
                //musicThread.nextSong()
                nextSong()
            }
        })
*/


        iv_main_player_act_stop_btn.setOnClickListener {
           // if (iv_main_player_act_stop_btn.isSelected and (myService.currentDuration == 0)) {
            if (!iv_main_player_act_stop_btn.isSelected && selectedFlag == 0) {
                try {
                    startService()
/*                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer = null
                }*/
                    //musicThread.playAudio(sourceMusicArray[currentPosition])
                    playAudio(sourceMusicArray[currentPosition])

                } catch (e: Exception) {
                    e.printStackTrace()
                    //Log.e("ERROR", mediaPlayer.toString())
                }

            } else if (iv_main_player_act_stop_btn.isSelected) {
                //musicThread.pauseAudio()
                pauseAudio()

            } else {
                //musicThread.restart()
                restart()
            }
            iv_main_player_act_stop_btn.isSelected = !iv_main_player_act_stop_btn.isSelected


        }

    }

    //미디어를 재생하는 사용자 정의 메소드
    fun playAudio(url: String) {



        var play_duration = myService.getDuration()
        var lenthOfSong =
            String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))

        tv_main_player_length_of_song.setText(lenthOfSong)

        isPlaying = true

        seekbar.setMax(play_duration)
        //seekBarThread.start()
    }


    fun prevSong() {

        if (currentPosition > 0) {
            //mediaPlayer.reset()
            currentPosition -= 1
            playAudio(sourceMusicArray[currentPosition])

        } else {
            killMediaPlayer()
            //mediaPlayer.release()
        }

    }

    fun nextSong() {

        if (currentPosition < sourceMusicArray.size) {
           // mediaPlayer.reset()
            currentPosition += 1
            playAudio(sourceMusicArray[currentPosition])

        } else {
            killMediaPlayer()
        }

    }

    fun pauseAudio() {

        isPlaying = false
        //musicPause()
        playbackPosition = myService.currentDuration
        musicPause()

    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경

        //mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        restartMusic()
        //mediaPlayer.start() // 시작

        // seekBarThread.run()
    }

    fun killMediaPlayer() {
/*      if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*/
        //mediaPlayer!!.release()
    }

    fun startService() {
        val serviceIntent = Intent(this, MyForeGroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        serviceIntent.putExtra("flag", 0)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService() {
        val serviceIntent = Intent(this, MyForeGroundService::class.java)
        stopService(serviceIntent)
    }

    fun musicPause(){
        val pauseIntent = Intent(this, MyForeGroundService::class.java)
        pauseIntent.putExtra("inputExtra", "중지")
        pauseIntent.putExtra("flag", 1)
        stopService(pauseIntent)
    }

    fun restartMusic(){
        val restartIntent = Intent(this, MyForeGroundService::class.java)
        restartIntent.putExtra("inputExtra", "재시작")
        restartIntent.putExtra("flag", 2)
        restartIntent.putExtra("playbackPosition", playbackPosition)
        stopService(restartIntent)
    }
}