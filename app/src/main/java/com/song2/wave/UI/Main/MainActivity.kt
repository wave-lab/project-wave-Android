package com.song2.wave.UI.Main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.song2.wave.UI.Main.Library.LibraryFragment
import com.song2.wave.UI.Main.Scoring.ScoringFragment
import com.song2.wave.UI.Main.Search.SearchFragment
import android.util.Log
import com.song2.wave.UI.Main.Home.HomeFragment
import com.song2.wave.UI.Main.MyPage.PointHistoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.song2.wave.Util.Interface.OnBackPressedListener

import android.content.pm.PackageManager

import android.graphics.Color
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.MyPageHitSuccessFragment
import android.Manifest.permission
import android.Manifest.permission.READ_CONTACTS
import android.content.*
import android.net.Uri
import android.support.v4.app.ActivityCompat
import com.song2.wave.Data.GET.GetRecommendResponse
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.Data.model.PlaySongData
import com.song2.wave.Data.model.Scoring.PassedCompletedSongData
import com.song2.wave.Data.model.Scoring.PassedSongData
import com.song2.wave.UI.Main.MyPage.MyPageFragment
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import com.song2.wave.Util.Audio.AudioApplication
import com.song2.wave.Util.Audio.BroadcastActions
import com.song2.wave.Util.Network.ApiClientSec
import com.song2.wave.Util.Network.SecondNetworkService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_player.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    var TAG = "MainActivity"
    lateinit var nowFrag: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.song2.wave.R.layout.activity_main)
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
            return;
        }

        iv_main_act_bottom_play.setOnClickListener {
            // 재생 또는 일시정지
            getSearch()
            //AudioApplication.getInstance().serviceInterface.togglePlay()
        }

        mainActivity = this
        callFragment("home")
        addBottomTab()
        backPressInFragment()
        registerBroadcast()
        updateUI()
    }

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateUI()
        }
    }

    // 리스너 객체 생성
    var mBackListener : OnBackPressedListener? = null
    var listenerFlag : Int = 0

    // 리스너 설정 메소드
    fun setOnBackPressedListener(listener: OnBackPressedListener?, flag: Int ) {
        mBackListener = listener
        listenerFlag = flag
    }

    fun backPressInFragment(){

    }

    fun addBottomTab() {

        ll_main_act_home_tab!!.setOnClickListener { callFragment("home")
            //클릭시 텍스트 색상변경
            txt_main_act_home.setTextColor(Color.parseColor("#00b6de"))
            txt_main_act_scoring.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_search.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_library.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_mypage.setTextColor(Color.parseColor("#9e9e9e"))

            //클릭시 이미지 색변경
            ib_main_act_home_tab.setImageResource(R.drawable.tab_btn_home_active)
            ib_main_act_scoring_tab.setImageResource(R.drawable.tab_btn_scoring_def)
            ib_main_act_search_tab.setImageResource(R.drawable.tab_btn_search_def)
            ib_main_act_library_tab.setImageResource(R.drawable.tab_btn_library_def)
            ib_main_act_mypage_tab.setImageResource(R.drawable.tab_btn_mypage_def)

        }


        ll_main_act_search_tab!!.setOnClickListener { callFragment("search")
            txt_main_act_home.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_scoring.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_search.setTextColor(Color.parseColor("#00b6de"))
            txt_main_act_library.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_mypage.setTextColor(Color.parseColor("#9e9e9e"))

            ib_main_act_home_tab.setImageResource(R.drawable.tab_btn_home_def)
            ib_main_act_scoring_tab.setImageResource(R.drawable.tab_btn_scoring_def)
            ib_main_act_search_tab.setImageResource(R.drawable.tab_btn_search_active)
            ib_main_act_library_tab.setImageResource(R.drawable.tab_btn_library_def)
            ib_main_act_mypage_tab.setImageResource(R.drawable.tab_btn_mypage_def)
        }

        ll_main_act_scoring_tab!!.setOnClickListener { callFragment("scoring")
            txt_main_act_home.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_scoring.setTextColor(Color.parseColor("#00b6de"))
            txt_main_act_search.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_library.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_mypage.setTextColor(Color.parseColor("#9e9e9e"))

            ib_main_act_home_tab.setImageResource(R.drawable.tab_btn_home_def)
            ib_main_act_scoring_tab.setImageResource(R.drawable.tab_btn_scoring_active)
            ib_main_act_search_tab.setImageResource(R.drawable.tab_btn_search_def)
            ib_main_act_library_tab.setImageResource(R.drawable.tab_btn_library_def)
            ib_main_act_mypage_tab.setImageResource(R.drawable.tab_btn_mypage_def)
        }
        ll_main_act_library_tab!!.setOnClickListener { callFragment("library")
            txt_main_act_home.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_scoring.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_search.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_library.setTextColor(Color.parseColor("#00b6de"))
            txt_main_act_mypage.setTextColor(Color.parseColor("#9e9e9e"))

            ib_main_act_home_tab.setImageResource(R.drawable.tab_btn_home_def)
            ib_main_act_scoring_tab.setImageResource(R.drawable.tab_btn_scoring_def)
            ib_main_act_search_tab.setImageResource(R.drawable.tab_btn_search_def)
            ib_main_act_library_tab.setImageResource(R.drawable.tab_btn_library_active)
            ib_main_act_mypage_tab.setImageResource(R.drawable.tab_btn_mypage_def)
        }
        ll_main_act_mypage_tab!!.setOnClickListener { callFragment("myPage")
            txt_main_act_home.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_scoring.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_search.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_library.setTextColor(Color.parseColor("#9e9e9e"))
            txt_main_act_mypage.setTextColor(Color.parseColor("#00b6de"))

            ib_main_act_home_tab.setImageResource(R.drawable.tab_btn_home_def)
            ib_main_act_scoring_tab.setImageResource(R.drawable.tab_btn_scoring_def)
            ib_main_act_search_tab.setImageResource(R.drawable.tab_btn_search_def)
            ib_main_act_library_tab.setImageResource(R.drawable.tab_btn_library_def)
            ib_main_act_mypage_tab.setImageResource(R.drawable.tab_btn_mypage_active)
        }

    }

    fun callFragment(frag: String) {

        when (frag) {
            "home" -> {
                nowFrag = HomeFragment()
            }
            "search" -> {
                nowFrag = SearchFragment()
            }
            "scoring" -> {
                nowFrag = ScoringFragment()
            }
            "library" -> {
                nowFrag = LibraryFragment()
            }
            "myPage" ->{
                nowFrag = MyPageHitSuccessFragment()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.song2.wave.R.id.main_fragment_container, nowFrag)
        transaction.commit()

    }

    override fun onBackPressed() {

        // SearchArtistFragment
        if (mBackListener != null && listenerFlag == 1) {
            Log.v(TAG, "아티스트 세부사항 프래그먼트 실행")
            mBackListener!!.onBackPressed()
            Log.e("!!!", "Listener is not null")
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        }
        else if(mBackListener != null && listenerFlag == 2){
            // SearchHomeFrament && SearchResult
            if (mBackListener != null && listenerFlag == 2) {
                Log.v(TAG, "검색 결과")
                mBackListener!!.onBackPressed()
                Log.e("!!!", "Listener is not null")
                // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
                // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
            }
        }
        else {
            Log.v(TAG, "MainActivity onBackPressed")
        }

    }

    private fun registerBroadcast() {
        val filter = IntentFilter()
        filter.addAction(BroadcastActions.PREPARED)
        filter.addAction(BroadcastActions.PLAY_STATE_CHANGED)
        registerReceiver(mBroadcastReceiver, filter)
    }

    private fun unregisterBroadcast() {
        unregisterReceiver(mBroadcastReceiver)
    }


    private fun updateUI() {
        if (AudioApplication.getInstance().serviceInterface.isPlaying) {
            iv_main_act_bottom_play.setImageResource(R.drawable.btn_stop_md)
        } else {
            iv_main_act_bottom_play.setImageResource(R.drawable.btn_play_md)
        }

       // var coverArtistName : String = AudioApplication.getInstance().serviceInterface.coverartistName

        if (AudioApplication.getInstance().serviceInterface.originArtistName != null) {
//            val albumArtUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), audioItem.mAlbumId)
            //Glide.with(applicationContext).load(albumArtUri).into(vp_main_player_act_cover_img)
            tv_main_act_bottom_title_origin_artist.setText(AudioApplication.getInstance().serviceInterface.songTitle + " - " + AudioApplication.getInstance().serviceInterface.originArtistName)
            tv_main_act_bottom_cover_artist.setText(AudioApplication.getInstance().serviceInterface.coverartistName)
        } else {
            tv_main_act_bottom_title_origin_artist.setText("재생중인 음악이 없습니다.")
        }
    }
    companion object {
        lateinit var mainActivity : MainActivity
    }

    fun getSearch() {

        var networkService2 = ApiClientSec.getRetrofit().create(SecondNetworkService::class.java)
        val getRatedResponse = networkService2.getSearch()


        getRatedResponse.enqueue(object : retrofit2.Callback<GetSearchResponse> {
            override fun onFailure(call: Call<GetSearchResponse>, t: Throwable) {
                Log.v("recommend song list fail", "test")
            }

            override fun onResponse(call: Call<GetSearchResponse>, response: Response<GetSearchResponse>) {
                if (response.isSuccessful) {
                    Log.v("recommend song list fail", "test2")

                }
                else{
                    Log.v("recommend song list fail", "test3")

                }
            }
        })
    }
}
