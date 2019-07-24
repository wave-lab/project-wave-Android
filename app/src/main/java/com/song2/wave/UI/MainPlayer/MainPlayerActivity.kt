package com.song2.wave.UI.MainPlayer

import android.app.Dialog
import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.song2.wave.Util.Audio.AudioApplication
import com.song2.wave.Util.Audio.AudioService
import com.song2.wave.Util.Audio.BroadcastActions
import com.song2.wave.Data.GET.GetSongDetailResponse
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.Adapter.CoverImgViewPager
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import com.song2.wave.Util.Player.Service.MyForeGroundService
import kotlinx.android.synthetic.main.activity_main_player.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_report.*


class MainPlayerActivity : AppCompatActivity(), View.OnClickListener {

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
    var rating_flag : Int = 0

    lateinit var durationTimeTv : TextView
    lateinit var lengthTimeTv : TextView

    var prevSongIdx = 0

    var mPosition : Int = 0
    var authorization_info : String = ""

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

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateUI()
        }
    }

    // 플레이어 재생, 정지 버튼 UI 업데이트
    private fun updateUI() {
        if (AudioApplication.getInstance().serviceInterface.isPlaying) {
            iv_main_player_act_stop_btn.setImageResource(R.drawable.btn_stop_md)
        } else {
            iv_main_player_act_stop_btn.setImageResource(R.drawable.btn_play_md)
        }
        val audioItem = AudioApplication.getInstance().serviceInterface.audioItem
        if (audioItem != null) {
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

        // 노래 선택으로 입장
        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

        val extras = intent.extras

        // 노래 선택으로 입장 시
        if (intent.getStringExtra("songUrl") != null) {
            Log.v("asdf", "선택 - 노래")
            _id = intent.getStringExtra("_id")
            title = intent.getStringExtra("title")
            originArtist = intent.getStringExtra("originArtist")
            coverArtist = intent.getStringExtra("coverArtist")
            songUrl = intent.getStringExtra("songUrl")
            rating_flag = intent.getIntExtra("rating_flag", 0)
            AudioApplication.getInstance().serviceInterface.play(applicationContext, _id, songUrl, originArtist, coverArtist,  title, rating_flag) // 선택한 오디오재생
        }
        // notification으로 입장 시
        else {
            Log.v("asdf", "선택 - 노티피케이션")
            _id = intent.getStringExtra("_id")
            title = extras.getString("title")
            originArtist = extras.getString("originArtist")
            coverArtist = extras.getString("coverArtist")
            rating_flag = intent.getIntExtra("rating_flag", 0)
        }
        if(flag == 0){
            songUrl = intent.getStringExtra("songUrl")
        }

        // 평가 목적으로 화면 들어왔을 경우
        if(rating_flag == 1){
            iv_main_player_act_report.visibility = View.VISIBLE // 신고 버튼 활성화
            iv_main_player_act_more.visibility = View.GONE // 더보기 버튼 비활성화
        }
        // 평가 목적으로 들어오지 않은 경우
        else{
            iv_main_player_act_report.visibility = View.GONE // 신고 버튼 비활성화
            iv_main_player_act_more.visibility = View.VISIBLE // 더보기 버튼 활성화
        }

        // 곡 세부사항 데이터 가져오기
        getSongDetail()

        // 곡 정보에 관한 데이터로 화면 구성
        tv_main_player_act_title_sing.text = title + " - " + originArtist
        tv_main_player_cover_artist_name.text = "Covered by " + coverArtist
        img_main_player_act_cover_img.visibility = View.VISIBLE
        vp_main_player_act_cover_img.visibility = View.INVISIBLE
        Glide.with(this).load(songImgUrl).into(img_main_player_act_cover_img)

        // Seekbar 등록
        addSeekBar()

        // 브로드캐스트 등록
        registerBroadcast();

        // 화면 업데이트
        updateUI();

        // 이미지 커버 사진 클릭 시
        img_main_player_act_cover_img.setOnClickListener {
            // 평가 목적인 경우, 평가 화면 활성화
            if(rating_flag == 1){
                rl_main_player_act_trans.visibility = View.VISIBLE
                rl_main_player_act_rating.visibility = View.VISIBLE
                ll_main_player_act_commnet.visibility = View.INVISIBLE
            }
        }

        // 화면 전체를 클릭 시
        rl_main_player_act_all.setOnClickListener {
            rl_main_player_act_trans.visibility = View.GONE
        }

        // 좋아요 버튼 클릭시
        iv_main_player_like_btn.setOnClickListener {
            iv_main_player_like_btn.isSelected = !iv_main_player_like_btn.isSelected
        }

        // 코멘트 내용 클릭시
        btn_main_player_act_comment.setOnClickListener {
            ll_main_player_act_commnet.visibility = View.VISIBLE
            rl_main_player_act_rating.visibility = View.INVISIBLE
        }

        // 화면 닫기 버튼 이벤트
        iv_maim_player_close.setOnClickListener {
            onBackPressed()
        }

        // 신고 버튼 클릭시
        iv_main_player_act_report.setOnClickListener {
            showReportDialog()
        }
    }

    // 신고 커스텀 다이얼로그 메서드
    protected fun showReportDialog() {
        var reportDialog = Dialog(this)
        reportDialog.setCancelable(true)
        reportDialog.getWindow().setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        val exitDialogView = this!!.layoutInflater.inflate(R.layout.dialog_report, null)
        reportDialog.setContentView(exitDialogView)

        // 신고 완료 버튼 클릭 시
        reportDialog.tv_report_dialog_confirm.setOnClickListener {
           Toast.makeText(applicationContext, "신고 완료", Toast.LENGTH_LONG).show()
            reportDialog.dismiss()
        }

        // 신고 취소 버튼 클릭 시
        reportDialog.tv_report_dialog_cancel.setOnClickListener {
            Toast.makeText(applicationContext, "취소", Toast.LENGTH_LONG).show()
            reportDialog.dismiss()
        }
        reportDialog.show()
    }

    // 브로드캐스트 등록
    private fun registerBroadcast() {
        val filter = IntentFilter()
        filter.addAction(BroadcastActions.PREPARED)
        filter.addAction(BroadcastActions.PLAY_STATE_CHANGED)
        registerReceiver(mBroadcastReceiver, filter)
    }

    // 화면 종료시
    override fun onDestroy() {
        super.onDestroy()
        unregisterBroadcast()
        //musicThread.killMediaPlayer()
    }

    private fun unregisterBroadcast() {
        unregisterReceiver(mBroadcastReceiver)
    }

    //seekbar touchListener
    fun addSeekBar() {
        seekbar = sb_scoring_player_act_seekbar
        seekbar.setOnClickListener(this)
    }

    fun prevSong() {
    }

    fun nextSong() {
    }

    fun restart() {

        isPlaying = true // 재생하도록 flag 변경
        //mediaPlayer.seekTo(playbackPosition) // 일시정지 시점으로 이동
        restartMusic()
        //mediaPlayer.start() // 시작
        // seekBarThread.run()
    }

    fun restartMusic(){
        val restartIntent = Intent(this, MyForeGroundService::class.java)
        restartIntent.putExtra("inputExtra", "재시작")
        restartIntent.putExtra("flag", 2)
        restartIntent.putExtra("playbackPosition", playbackPosition)
        stopService(restartIntent)
    }

    companion object {
        lateinit var mainPlayerActivity: MainPlayerActivity
        //일종의 스태틱
    }

    // 곡 세부사항 get 통신
    fun getSongDetail()
    {
        val getSongDetailResponse = networkService.getSongDetailResonse(authorization_info, _id)
        getSongDetailResponse.enqueue(object : Callback<GetSongDetailResponse> {

            override fun onResponse(call: Call<GetSongDetailResponse>, response: Response<GetSongDetailResponse>) {
                Log.v("TAG", "곡 세부사항 GET 통신 성공")
                if(response.isSuccessful){
                    var data = response!!.body()!!.data
                    Glide.with(applicationContext).load(data.artwork).into(img_main_player_act_cover_img)

                    var genreValue : String = ""
                    for(i in 0.. data.genre.size-1){
                        genreValue += data.genre[i] + " "
                        if(i == data.genre.size-1)
                            genreValue += data.genre[i]
                    }
                    tv_main_player_act_genre.text = genreValue
                    Glide.with(applicationContext).load(data.artwork)
                }else{
                    Log.v("MainPlayerActivity", "통신 에러 = " + response.code())
                }
            }

            override fun onFailure(call: Call<GetSongDetailResponse>, t: Throwable?) {
                Toast.makeText(applicationContext,"서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

}