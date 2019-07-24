package com.song2.wave.UI.Main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.song2.wave.UI.Main.Library.LibraryFragment
import com.song2.wave.UI.Main.Scoring.ScoringFragment
import android.util.Log
import com.song2.wave.UI.Main.Home.HomeFragment
import com.song2.wave.UI.Main.MyPage.PointHistoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.song2.wave.Util.Interface.OnBackPressedListener

import android.content.pm.PackageManager

import android.graphics.Color
import com.song2.wave.R
import android.content.*
import android.support.v4.app.FragmentManager
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.UI.Main.MyPage.MyPageFragment
import com.song2.wave.Util.Audio.AudioApplication
import com.song2.wave.Util.Audio.BroadcastActions
import com.song2.wave.Util.Network.ApiClientSec
import com.song2.wave.Util.Network.SecondNetworkService
import retrofit2.Call
import retrofit2.Response
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import com.song2.wave.R.id.iv_main_act_bottom_play
import com.song2.wave.UI.Main.Search.SearchHomeFragment
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import kotlinx.android.synthetic.main.activity_player.*
import kotlin.math.min
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    var FINISH_INTERVAL_TIME = 2000
    var backPressedTime : Long = 0

    var home= false
    var search= false
    var scoring= false
    var library= false
    var mypage= false

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    var TAG = "MainActivity"
    lateinit var nowFrag: Fragment
    lateinit var mainSb : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.song2.wave.R.layout.activity_main)


        addFragment(HomeFragment())
        addFragment(SearchHomeFragment())
        addFragment(ScoringFragment())
        addFragment(LibraryFragment())
        addFragment(MyPageFragment())


        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
            return
        }

        // 하단 미니 플레이어 클릭 시 메인 플레이어 화면으로 이벤트
        ll_main_act_bottom_main_player.setOnClickListener {
            val intent = Intent(applicationContext, MainPlayerActivity::class.java)
            intent.putExtra("_id", AudioApplication.getInstance().serviceInterface.songID)
            intent.putExtra("title", AudioApplication.getInstance().serviceInterface.songTitle)
            intent.putExtra("originArtist", AudioApplication.getInstance().serviceInterface.originArtistName )
            intent.putExtra("coverArtist", AudioApplication.getInstance().serviceInterface.coverartistName)
            intent.putExtra("songUrl", AudioApplication.getInstance().serviceInterface.songUrlValue)
            intent.putExtra("rating_flag", AudioApplication.getInstance().serviceInterface.ratingFlagValue)
            startActivity(intent)
        }

        iv_main_act_bottom_play.setOnClickListener {
            // 재생 또는 일시정지
            AudioApplication.getInstance().serviceInterface.togglePlay()
        }

        mainSb = findViewById(R.id.seekbar_main_act_seek);

        mainSb.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                return true
            }
        })

        mainActivity = this
        replaceFragment(HomeFragment())
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
/*    fun setOnBackPressedListener(listener: OnBackPressedListener?, flag: Int ) {
        mBackListener = listener
        listenerFlag = flag
    }*/

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

        if(frag == "home"){

            supportFragmentManager.beginTransaction().show(HomeFragment()).commit()

            supportFragmentManager.beginTransaction().hide(SearchHomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(ScoringFragment()).commit()
            supportFragmentManager.beginTransaction().hide(LibraryFragment()).commit()
            supportFragmentManager.beginTransaction().hide(MyPageFragment()).commit()
        }

        //
        if(frag == "search"){
            supportFragmentManager.beginTransaction().show(SearchHomeFragment()).commit()

            supportFragmentManager.beginTransaction().hide(HomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(ScoringFragment()).commit()
            supportFragmentManager.beginTransaction().hide(LibraryFragment()).commit()
            supportFragmentManager.beginTransaction().hide(MyPageFragment()).commit()
        }
        else{
            Log.v("search","re ADD")
        }

        //
        if(frag == "scoring") {

            supportFragmentManager.beginTransaction().show(ScoringFragment()).commit()

            supportFragmentManager.beginTransaction().hide(HomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(SearchHomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(LibraryFragment()).commit()
            supportFragmentManager.beginTransaction().hide(MyPageFragment()).commit()
        }
        else{
            Log.v("scoring","re ADD")

        }

        //
        if(frag == "library"){
            supportFragmentManager.beginTransaction().show(LibraryFragment()).commit()

            supportFragmentManager.beginTransaction().hide(HomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(SearchHomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(ScoringFragment()).commit()
            supportFragmentManager.beginTransaction().hide(MyPageFragment()).commit()
        }
        else{
            Log.v("library","re ADD")


        }

        //
        if(frag == "myPage") {
            supportFragmentManager.beginTransaction().show(MyPageFragment()).commit()

            supportFragmentManager.beginTransaction().hide(HomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(SearchHomeFragment()).commit()
            supportFragmentManager.beginTransaction().hide(ScoringFragment()).commit()
            supportFragmentManager.beginTransaction().hide(LibraryFragment()).commit()
        }
        else{
//            fragmentArray.add(MyPageFragment())
            Log.v("myPage","re ADD")


        }

/*        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.song2.wave.R.id.main_fragment_container, nowFrag)
        transaction.commit()   */

/*        var fm = supportFragmentManager
        fm.beginTransaction().show(nowFrag).commit()
        Log.v("showFrag",nowFrag.toString())*/
/*        var fm = supportFragmentManager

        for (i in fragmentArray.indices){
            fm.beginTransaction().hide(fragmentArray[i]).commit()
        }*/

    }

    override fun onBackPressed() {

        //메인 탭에 위치 햘 경만 적용 되도록 해야 할 것 같음....
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed()
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "'뒤로' 버튼을 한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        //

/*
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
        }*/

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

    fun addFragment(fragment : android.support.v4.app.Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_fragment_container, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: android.support.v4.app.Fragment)
    {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
