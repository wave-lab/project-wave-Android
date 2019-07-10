package com.song2.wave.UI.MainPlayer

import android.content.*
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.song2.wave.AudioTest.AudioApplication
import com.song2.wave.AudioTest.AudioService
import com.song2.wave.AudioTest.BroadcastActions
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.Adapter.CoverImgViewPager
import com.song2.wave.Util.Player.Service.MyForeGroundService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_player.*
import kotlinx.android.synthetic.main.activity_set_start_point.*
import java.util.*

class MainPlayerActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var playTime: String
    var playbackPosition : Int = 0
    var isPlaying : Boolean = false
    lateinit var seekbar: SeekBar
    lateinit var myService : MyForeGroundService
    lateinit var audioService : AudioService
    lateinit var songUrl : String
    var title: String = ""
    var originArtist : String = ""
    var coverArtist : String = ""
    var songImgUrl : String = ""
    var flag : Int = 0

    var selectedFlag : Int = 0



    var currentPosition = 0
    var prevSongIdx = 0

    var mPosition : Int = 0


    // var n_sbHandler = sbHandler()
     //var seekBarThread = sbThread()

    override fun onClick(v: View) {
        when (v.id) {
            R.id.lin_miniplayer -> {
            }

            R.id.iv_main_player_act_stop_btn ->
                // 재생 또는 일시정지
                AudioApplication.getInstance().serviceInterface.togglePlay()

        }// 플레이어 화면으로 이동할 코드가 들어갈 예정
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
//                page.setScaleY(normalizedposition / 2 + 0.8f)
                page.setScaleY(normalizedposition / 2 + 0.65f)
            }

        })


        coverImgViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

                Log.v("onPageScrollStateChanged", state.toString())

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                Log.e("onPageScrolled-position", position.toString()+ positionOffset.toString() + positionOffsetPixels.toString())

                if(position < prevSongIdx)
                    prevSong()
                else
                    nextSong()

                prevSongIdx = position


                //이전으로 가면 preview
            }

            override fun onPageSelected(position: Int) {
                Log.v("onPageSeleted", position.toString())
            }

        })


    }

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
                    if(seekbar.max > 0){
                        playTime = String.format(
                                "%02d:%02d",
                                audioService.getMpCurrentPosition1(),
                                audioService.getMpCurrentPosition2()
                        )

                        tv_main_player_duration_time.setText(playTime)
                        seekbar.setProgress(audioService.getCurrentPosition())
                    }

                }
            }
        }

        /////////// / Timer 생성 //////////////
        val timer = Timer()
        timer.schedule(tt, 0, 1000)
        //////////////////////////////////////

    }

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateUI()
        }
    }

    private fun updateUI() {
        if (AudioApplication.getInstance().serviceInterface.isPlaying) {
            iv_main_player_act_stop_btn.setImageResource(R.drawable.btn_stop_md)
        } else {
            iv_main_player_act_stop_btn.setImageResource(R.drawable.btn_play_md)
        }
        val audioItem = AudioApplication.getInstance().serviceInterface.audioItem
        if (audioItem != null) {
            val albumArtUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), audioItem.mAlbumId)
           //Glide.with(applicationContext).load(albumArtUri).into(vp_main_player_act_cover_img)
            tv_main_player_act_title_sing.setText(audioItem.mTitle)
        } else {
            vp_main_player_act_cover_img.setBackgroundResource(R.drawable.kakao_default_profile_image)
            //tv_main_player_act_title_sing.setText("재생중인 음악이 없습니다.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_player)

        mainPlayerActivity = this
        myService = MyForeGroundService()
        audioService = AudioService()

        iv_main_player_act_stop_btn.setOnClickListener(this)
        mPosition = intent.getIntExtra("mPosition", 0)

        flag = intent.getIntExtra("flag", 0)

        // 노래 선택으로 입장

        val extras = intent.extras

        // 노래 선택으로 입장 시
        if (extras == null) {
            title = intent.getStringExtra("title")
            originArtist = intent.getStringExtra("originArtist")
            coverArtist = intent.getStringExtra("coverArtist")
            songImgUrl = intent.getStringExtra("songImgUrl")
            songUrl = intent.getStringExtra("songUrl")
            AudioApplication.getInstance().serviceInterface.play(songUrl, originArtist, coverArtist,  title, songImgUrl) // 선택한 오디오재생
        }
        // notification으로 입장 시
        else {
            title = extras.getString("title")
            originArtist = extras.getString("originArtist")
            coverArtist = extras.getString("coverArtist")
            songImgUrl = extras.getString("songImgUrl")
        }
        if(flag == 0){
            songUrl = intent.getStringExtra("songUrl")

        }

        Log.v("Asdf","플래그 = " + flag)


        tv_main_player_act_title_sing.text = title + " - " + originArtist
        tv_main_player_cover_artist_name.text = "Covered by " + coverArtist
        img_main_player_act_cover_img.visibility = View.VISIBLE
        vp_main_player_act_cover_img.visibility = View.INVISIBLE

        Glide.with(this).load(songImgUrl).into(img_main_player_act_cover_img)

        initialSetting()

        playerBtn()
        registerBroadcast();
        updateUI();

        iv_main_player_like_btn.setOnClickListener {
            iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
        }

    }

    private fun registerBroadcast() {
        val filter = IntentFilter()
        filter.addAction(BroadcastActions.PREPARED)
        filter.addAction(BroadcastActions.PLAY_STATE_CHANGED)
        registerReceiver(mBroadcastReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterBroadcast()
        //musicThread.killMediaPlayer()
    }

    private fun unregisterBroadcast() {
        unregisterReceiver(mBroadcastReceiver)
    }

    fun initialSetting(){

        addSeekBar()
        //addTimer()

//        var play_duration = audioService.getDuration()
  //      var lengthOfSong =
    //            String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))
        //tv_set_start_act_length_of_song.setText(lengthOfSong)

      //  seekbar.setMax(play_duration)
    }


    //seekbar touchListener
    fun addSeekBar() {
        seekbar = sb_scoring_player_act_seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            //값을 변경 한 후 터치를 떼었을 때
            override fun onStopTrackingTouch(seekbar: SeekBar) {
                isPlaying = true
                playbackPosition = seekbar.progress
//                mediaPlayer.seekTo(playbackPosition)

                Log.e("onStopTrackingTouch", sb_scoring_player_act_seekbar.isSelected.toString())
//                if (!sb_scoring_player_act_seekbar.isSelected) {
//                    mediaPlayer.start()
//                } else
//                    mediaPlayer.pause()
            }

            //seek바의 값을 변경하기 위해 터치했을 때
            override fun onStartTrackingTouch(seekbar: SeekBar) {
                Log.e("onStartTrackingTouch", sb_scoring_player_act_seekbar.isSelected.toString())

                isPlaying = false
//                mediaPlayer.pause()
            }

            //seek바의 값이 변경되었을때 + fromUser: Boolean : 터치를 통해 변경했으면 false , 코드를 통하면 true
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekbar!!.getMax() == progress) {
                    isPlaying = false
//                    mediaPlayer.stop()
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

/*
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
*/
   }

   //미디어를 재생하는 사용자 정의 메소드
   fun playAudio(url: String) {



       var play_duration = audioService.getDuration()
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

    public companion object {
        lateinit var mainPlayerActivity: MainPlayerActivity
        //일종의 스태틱
    }
}