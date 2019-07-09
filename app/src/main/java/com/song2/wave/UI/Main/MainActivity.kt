package com.song2.wave.UI.Main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.song2.wave.UI.Main.Library.LibraryFragment
import com.song2.wave.UI.Main.Scoring.ScoringFragment
import com.song2.wave.UI.Main.Search.SearchFragment
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.MyPageFragment
import android.util.Log
import com.song2.wave.UI.Main.Home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.song2.wave.Util.Interface.OnBackPressedListener
import android.Manifest.permission
import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat





class MainActivity : AppCompatActivity() {

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    var TAG = "MainActivity"
    lateinit var nowFrag: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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


        mainActivity = this
        callFragment("home")

        addBottomTab()

        backPressInFragment()

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

        ll_main_act_home_tab!!.setOnClickListener { callFragment("home") }
        ll_main_act_scoring_tab!!.setOnClickListener { callFragment("scoring") }
        ll_main_act_search_tab!!.setOnClickListener { callFragment("search") }
        ll_main_act_library_tab!!.setOnClickListener { callFragment("library") }
        ll_main_act_mypage_tab!!.setOnClickListener { callFragment("myPage") }

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
                nowFrag = MyPageFragment()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, nowFrag)
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
/*
        val fragmentList = supportFragmentManager.fragments

        if (fragmentList != null) {
            //TODO: Perform your logic to pass back press here
            for (fragment in fragmentList) {
                if (fragment is OnBackPressedListener) {
                    (fragment as OnBackPressedListener).onBackPressed()
                }
            }
        }
        */
    }

    companion object {
        lateinit var mainActivity : MainActivity
    }

}
