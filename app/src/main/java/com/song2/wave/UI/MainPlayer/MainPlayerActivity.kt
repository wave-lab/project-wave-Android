package com.song2.wave.UI.MainPlayer

import android.app.Activity
import android.content.*
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.song2.wave.Util.Audio.AudioApplication
import com.song2.wave.Util.Audio.AudioService
import com.song2.wave.Util.Audio.BroadcastActions
import com.song2.wave.Data.GET.GetSongDetailResponse
import com.song2.wave.Data.POST.PostLogin
import com.song2.wave.Data.POST.PostRating
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.UI.MainPlayer.Adapter.CoverImgViewPager
import com.song2.wave.Util.DB.DBHelper
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import com.song2.wave.Util.Player.Service.MyForeGroundService
import kotlinx.android.synthetic.main.activity_main_player.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.song2.wave.Util.DB.DbOpenHelper
import com.song2.wave.Util.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup_first.*
import org.jetbrains.anko.toast
import org.json.JSONObject


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
    var _id : String = ""
    var ratingFlag = 0
    var score : Int = 0

    var selectedFlag : Int = 0
    lateinit var durationTimeTv : TextView
    lateinit var lengthTimeTv : TextView
    private var mDbOpenHelper: DbOpenHelper? = null

    var currentPosition = 0
    var prevSongIdx = 0

    var mPosition : Int = 0

    var authorization_info : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

    // var n_sbHandler = sbHandler()
     //var seekBarThread = sbThread()

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }


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
        durationTimeTv = findViewById(R.id.tv_main_player_duration_time)
        lengthTimeTv = findViewById(R.id.tv_main_player_length_of_song)

        iv_main_player_act_stop_btn.setOnClickListener(this)
        mPosition = intent.getIntExtra("mPosition", 0)

        flag = intent.getIntExtra("flag", 0)
        ratingFlag = intent.getIntExtra("rating_flag", 0)

        if(ratingFlag == 0){

        }
        else if(ratingFlag == 1){
            img_main_player_act_cover_img.setOnClickListener {
                rl_main_player_act_trans.visibility = View.VISIBLE
                rl_main_player_act_rating.visibility = View.VISIBLE
                ll_main_player_act_commnet.visibility = View.INVISIBLE
            }
            rl_main_player_act_all.setOnClickListener {
                rl_main_player_act_trans.visibility = View.INVISIBLE
            }

            iv_main_player_like_btn.setOnClickListener {
                iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
            }

            btn_main_player_act_comment.setOnClickListener {
                ll_main_player_act_commnet.visibility = View.VISIBLE
                rl_main_player_act_rating.visibility = View.INVISIBLE
            }
            rb_main_player_act_rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                score = rating.toInt()
                postRating()
            }
        }

        // 노래 선택으로 입장
        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

        val extras = intent.extras
        Log.v("","엑스트라 = "+extras)

        // 노래 선택으로 입장 시
        if (extras == null) {
            Log.v("asdf", "선택 - 노래")
            _id = intent.getStringExtra("_id")
            Log.v("asdf","받아온 값 = " + _id)
            title = intent.getStringExtra("title")
            originArtist = intent.getStringExtra("originArtist")
            coverArtist = intent.getStringExtra("coverArtist")
            songUrl = intent.getStringExtra("songUrl")
            AudioApplication.getInstance().serviceInterface.play(applicationContext, _id, songUrl, originArtist, coverArtist,  title) // 선택한 오디오재생

        }

        // notification으로 입장 시
        else {
            Log.v("asdf", "선택 - 노티피케이션")
            _id = intent.getStringExtra("_id")
            title = extras.getString("title")
            originArtist = extras.getString("originArtist")
            coverArtist = extras.getString("coverArtist")
        }
               if(flag == 0){
            songUrl = intent.getStringExtra("songUrl")
        }


        Log.v("Asdf","플래그 = " + flag)

        getSongDetail()

        tv_main_player_act_title_sing.text = title + " - " + originArtist
        tv_main_player_cover_artist_name.text =  coverArtist
        img_main_player_act_cover_img.visibility = View.VISIBLE
        vp_main_player_act_cover_img.visibility = View.INVISIBLE

        tv_main_player_artist_name.text = originArtist
        tv_main_player_act_introduce_short.text = "한 줄 코멘트"
        Glide.with(this).load(songImgUrl).into(img_main_player_act_cover_img)

        initialSetting()

        playerBtn()
        registerBroadcast();
        updateUI();

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    fun postRating()
    {
        val pref = applicationContext.getSharedPreferences("auto", Activity.MODE_PRIVATE)
        var token : String = ""
        token = pref.getString("token", "")

        val networkService = ApiClient.getRetrofit().create(NetworkService::class.java)
        var postRating = PostRating(score)
        var postLoginResponse = networkService.postRating(token, postRating)
        postLoginResponse.enqueue(object : retrofit2.Callback<PostResponse>{

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if(response.isSuccessful){
                    Log.v("TAG","평가 완료")
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
            }
        })
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

    fun getSongDetail()
    {
        val getSongDetailResponse = networkService.getSongDetailResonse(authorization_info, _id)
        getSongDetailResponse.enqueue(object : Callback<GetSongDetailResponse> {

            override fun onResponse(call: Call<GetSongDetailResponse>, response: Response<GetSongDetailResponse>) {
                Log.v("TAG", "곡 세부사항 통신 성공")
                if(response.isSuccessful){
                    var data = response!!.body()!!.data
                    Glide.with(applicationContext).load(data.artwork).into(img_main_player_act_cover_img)
                    val dbHelper = DBHelper(applicationContext)
                    dbHelper.insert(_id, data.songUrl, originArtist, coverArtist, title, data.artwork);
                    Log.v("asfd","디비 값 = " + (dbHelper.getResult()));

                    var genreValue : String = ""
                    for(i in 0.. data.genre.size-1){
                        genreValue += data.genre[i] + " "
                        if(i == data.genre.size-1)
                            genreValue += data.genre[i]
                    }
                    tv_main_player_act_genre.text = genreValue
                    Glide.with(applicationContext).load(data.artwork)
                    Log.v("Asf","테스트 장르 = " + genreValue)
                }else{
                    Log.v("ASdf", "테스트 에러 = " + response.code())
                }
            }

            override fun onFailure(call: Call<GetSongDetailResponse>, t: Throwable?) {
                Toast.makeText(applicationContext,"서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

}