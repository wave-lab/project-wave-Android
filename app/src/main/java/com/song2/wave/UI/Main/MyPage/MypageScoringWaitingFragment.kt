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
import com.song2.wave.Data.model.Mypage.ScoreWaitingData
import com.song2.wave.Data.model.PlayListData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.ScoreWaitingAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.ApplicationController
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_mypage_scoring_waiting.*
import retrofit2.Call
import retrofit2.Response

class MypageScoringWaitingFragment : Fragment(){


    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }
    lateinit var authorization_info : String

    lateinit var scoreWaitingData : ArrayList<ScoreWaitingData>
    lateinit var scoreWaitingAdapter : ScoreWaitingAdapter
    lateinit var requestManager: RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_scoring_waiting,container,false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

        attachRecyclerView()
        getUploadResponse()
    }

    fun attachRecyclerView() {
        scoreWaitingData = ArrayList()
        requestManager = Glide.with(this)

        //insertExampleData()
    }

    fun insertExampleData() {
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))

    }

    //upload
    fun getUploadResponse(){
        val getUploadResponse = networkService.getUploadResponse("application/json",authorization_info,"wait")

        getUploadResponse.enqueue(object : retrofit2.Callback<GetPlaylistResponse>{
            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                Log.e("mypage hits song waitting list", t.toString())
            }

            override fun onResponse(call: Call<GetPlaylistResponse>, response: Response<GetPlaylistResponse>) {
                if (response.isSuccessful) {
                    val playlistDataList: PlayListData = response.body()!!.data

                    if(playlistDataList == null){
                        return
                    }


                    for(i in playlistDataList.songList.indices)
                        scoreWaitingData.add(ScoreWaitingData(playlistDataList.songList[i].artwork, playlistDataList.songList[i].originTitle,playlistDataList.songList[i].originArtistName, playlistDataList.songList[i].genre))

                    scoreWaitingAdapter = ScoreWaitingAdapter(scoreWaitingData, requestManager)
                    rv_mypage_frag_score_waiting_list.adapter = scoreWaitingAdapter
                    rv_mypage_frag_score_waiting_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

        })

    }
}