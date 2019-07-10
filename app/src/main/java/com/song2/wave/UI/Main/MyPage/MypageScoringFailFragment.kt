package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.model.Mypage.ScoreResultData
import com.song2.wave.Data.model.PlayListData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.ScoreResultAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.ApplicationController
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_mypage_scoring_fail.*
import retrofit2.Call
import retrofit2.Response

class
MypageScoringFailFragment : Fragment(){

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }
    lateinit var authorization_info : String

    lateinit var failedScoreResultData: ArrayList<ScoreResultData>
    lateinit var failedScoreResultAdapter: ScoreResultAdapter
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_scoring_fail,container,false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"
        getUploadResponse()
        attachRecyclerView()
    }

    fun attachRecyclerView() {
        failedScoreResultData = ArrayList()

        requestManager = Glide.with(this)

        //insertExampleData()



    }

    fun insertExampleData() {
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))

    }

    //upload
    fun getUploadResponse(){
        val getUploadResponse = networkService.getUploadResponse("application/json",authorization_info,"fail")

        getUploadResponse.enqueue(object : retrofit2.Callback<GetPlaylistResponse>{
            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                Log.e("mypage hits song fail list", t.toString())
            }

            override fun onResponse(call: Call<GetPlaylistResponse>, response: Response<GetPlaylistResponse>) {
                if (response.isSuccessful) {
                    val playlistDataList: PlayListData = response.body()!!.data


                    if(playlistDataList == null){
                        return
                    }

                    for(i in playlistDataList.songList.indices)
                        failedScoreResultData.add(ScoreResultData(playlistDataList.songList[i].rateScore,playlistDataList.songList[i].artwork, playlistDataList.songList[i].originTitle,playlistDataList.songList[i].originArtistName, playlistDataList.songList[i].genre))

                    failedScoreResultAdapter = ScoreResultAdapter(failedScoreResultData, requestManager)
                    rv_mypage_frag_score_failed_list.adapter = failedScoreResultAdapter
                    rv_mypage_frag_score_failed_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

        })

    }
}