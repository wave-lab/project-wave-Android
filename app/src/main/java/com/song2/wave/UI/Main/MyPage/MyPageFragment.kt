package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.GET.GetUserInfoResponse
import com.song2.wave.Data.model.Mypage.ScoreHitResultData
import com.song2.wave.Data.model.PlayListData
import com.song2.wave.Data.model.UserInfoData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.FragmentMypageScoringResultPagerAdapter
import com.song2.wave.UI.Main.MyPage.Adapter.HitScoreResultAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.ApplicationController
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import retrofit2.Call
import retrofit2.Response

class MyPageFragment : Fragment() {

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }
    lateinit var authorization_info : String

    lateinit var hitScoreResultAdapter: HitScoreResultAdapter
    lateinit var scoreHitResultData: ArrayList<ScoreHitResultData>
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()

        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

        //통신
        getHitsResponse()
        getUserInfoResponse()
        attachRecyclerView()

    }

    fun attachRecyclerView() {
        scoreHitResultData = ArrayList()
        requestManager = Glide.with(this)

        //insertExampleData()
    }

    fun insertExampleData() {

        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))

    }

    private fun configureTopNavigation() {
        vp_mypage_frag_content.adapter = FragmentMypageScoringResultPagerAdapter(childFragmentManager, 3)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_mypage_frag_tabbar.setupWithViewPager(vp_mypage_frag_content)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_mypage_score_result_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_mypage_frag_tabbar.getTabAt(0)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_passed) as RelativeLayout
        tl_mypage_frag_tabbar.getTabAt(1)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_waiting) as RelativeLayout
        tl_mypage_frag_tabbar.getTabAt(2)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_fail) as RelativeLayout
    }

    fun getUserInfoResponse(){
        val getUserInfoResponse = networkService.getUserInfoResponse("application/json",authorization_info)

        getUserInfoResponse.enqueue(object : retrofit2.Callback<GetUserInfoResponse>{
            override fun onFailure(call: Call<GetUserInfoResponse>, t: Throwable) {
                Log.e("mypage userinfo fail", t.toString())
            }

            override fun onResponse(call: Call<GetUserInfoResponse>, response: Response<GetUserInfoResponse>) {
                val userInfoData : UserInfoData = response.body()!!.data

                showUserInfo(userInfoData)

            }
        })
    }

    //hits
    fun getHitsResponse(){
        val getHitsResponse = networkService.getHitsResponse("application/json",authorization_info,null)

        getHitsResponse.enqueue(object : retrofit2.Callback<GetPlaylistResponse>{
            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                Log.e("mypage hits song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetPlaylistResponse>, response: Response<GetPlaylistResponse>) {
                if (response.isSuccessful) {
                    val playlistDataList: PlayListData = response.body()!!.data

                    for(i in playlistDataList.songList.indices)
                    {
                        var songstatus : Boolean

                        if(playlistDataList.songList[i].songStatus == 1)
                            songstatus = true
                        else if (playlistDataList.songList[i].songStatus == 2)
                            songstatus = false
                        else
                            continue

                        Log.e("songstatus", songstatus.toString())

                        scoreHitResultData.add(ScoreHitResultData(songstatus,playlistDataList.songList[i].artwork, playlistDataList.songList[i].originTitle,playlistDataList.songList[i].originArtistName, playlistDataList.songList[i].coverArtistName))
                    }

                    hitScoreResultAdapter = HitScoreResultAdapter(scoreHitResultData, requestManager)
                    rv_mypage_frag_score_hit_result.adapter = hitScoreResultAdapter
                    rv_mypage_frag_score_hit_result.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }

        })
    }

    fun showUserInfo(userInfoData : UserInfoData){

        tv_mypage_frag_scoring_cnt.setText(userInfoData.rateSongCount.toString())
        tv_mypage_frag_perfect_cnt.setText(userInfoData.hitSongCount.toString())

        Glide.with(this).load(userInfoData.profileImg).into(cv_mypage_frag_profile_img)
        tv_mypage_frag_profile_name.setText(userInfoData.nickName)
        tv_mypage_frag_my_point.setText(userInfoData.totalPoint.toString()+" P")

        if(userInfoData.isArtist.equals(1))
            tv_home_frag_isArtist.setText("Artist")
        else
            tv_home_frag_isArtist.setText("Listener")

    }
}