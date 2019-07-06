package com.song2.wave.UI.MainPlayer

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.Adapter.CoverImgViewPager
import kotlinx.android.synthetic.main.activity_main_player.*
import java.util.*

class MainPlayerActivity : AppCompatActivity() {

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

    fun addTimer() {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_player)

        mediaPlayer = MediaPlayer()

        addCoverImgViewPager()

        addSeekBar()
        playerBtn()
        addTimer()

        iv_main_player_like_btn.setOnClickListener {
            iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
        }

    }

    fun addCoverImgViewPager() {


        var imageList = arrayListOf<String>(
            "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
            "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E",
            "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E",
            "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
            "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E",
            "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E"
        )

        var coverImgViewPager: ViewPager = findViewById(R.id.vp_main_player_act_cover_img)
        coverImgViewPager.setClipToPadding(false)

        val d = resources.displayMetrics.density
        val margin = (60 * d).toInt()
        val marginRight = (60 * d).toInt()


        coverImgViewPager.setPadding(margin, 0, marginRight, 0)
        coverImgViewPager.setAdapter(CoverImgViewPager(this, imageList))

        coverImgViewPager.setPageTransformer(false, object : ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                var normalizedposition = Math.abs(Math.abs(position) - 1)

                page.setScaleX(normalizedposition / 2 + 0.65f)
                page.setScaleY(normalizedposition / 2 + 0.65f)
            }

        })




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

                mediaPlayer.start()

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
                nextSong()
            }
        })



        iv_main_player_act_stop_btn.setOnClickListener {
            if (iv_main_player_act_stop_btn.isSelected and (mediaPlayer.getCurrentPosition() == 0)) {
                try {
/*                if (mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer = null
                }*/
                    //musicThread.playAudio(sourceMusicArray[currentPosition])
                    playAudio(sourceMusicArray[currentPosition])

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("ERROR", mediaPlayer.toString())
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

        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()

        var play_duration = mediaPlayer!!.getDuration()
        var lenthOfSong =
            String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))

        tv_main_player_length_of_song.setText(lenthOfSong)

        isPlaying = true

        seekbar.setMax(play_duration)
        //seekBarThread.start()
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

    fun pauseAudio() {

        isPlaying = false

        playbackPosition = mediaPlayer.getCurrentPosition()
        mediaPlayer!!.pause()

    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경

        mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        mediaPlayer.start() // 시작

        // seekBarThread.run()
    }

    fun killMediaPlayer() {
/*      if (mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.release()
        }*/
        mediaPlayer!!.release()
    }
}
