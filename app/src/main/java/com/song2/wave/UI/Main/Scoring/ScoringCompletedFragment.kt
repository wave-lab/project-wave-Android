package com.song2.wave.UI.Main.Scoring

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.Data.GET.GetRecommendResponse
import com.song2.wave.Data.model.PlaySongData
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringFailedRecyclerViewAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassedRecyclerViewAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassingRecyclerViewAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_scoring_completed.*
import retrofit2.Call
import retrofit2.Response


class ScoringCompletedFragment : Fragment(){

    var dataListFailed: ArrayList<SongData> = ArrayList()
    var dataListPassed: ArrayList<SongData> = ArrayList()
    var dataListPassing: ArrayList<SongData> = ArrayList()

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    lateinit var authorization_info : String

    lateinit var scoringPassedRecyclerViewAdapter : ScoringPassedRecyclerViewAdapter
    lateinit var scoringPassingRecyclerViewAdapter : ScoringPassingRecyclerViewAdapter
    lateinit var scoringFailedRecyclerViewAdapter : ScoringFailedRecyclerViewAdapter
    var songArr = ArrayList<String?>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scoring_completed,container,false)
        songArr.add("ASdf")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var pref = context!!.getSharedPreferences("auto", Activity.MODE_PRIVATE)

        //authorization_info = pref.getString("token","")
        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxNiwiaWF0IjoxNTYyOTY3NzY2LCJleHAiOjE1NjU1NTk3NjZ9.PmlhTASv3yT75I_RG9T6YRL-BdCAGZaE7fpB4r_G3BM"

        getRatedResponse()
    }


    fun getRatedResponse(status : String){

        val getRatedResponse = networkService.getRatedResponse("application/json",authorization_info,status)


        getRatedResponse.enqueue(object : retrofit2.Callback<GetRecommendResponse>{
            override fun onFailure(call: Call<GetRecommendResponse>, t: Throwable) {
                Log.e("recommend song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetRecommendResponse>, response: Response<GetRecommendResponse>) {
                if (response.isSuccessful) {
                    val playSongDataList: ArrayList<PlaySongData> = response.body()!!.data

                    if(playSongDataList == null)
                        return

                    if(status == "pass"){
                        Log.e("pass","status == pass")
                        for(i in playSongDataList.indices) {
                            dataListPassed.add(
                                SongData(playSongDataList[i]._id, playSongDataList[i].songUrl,
                                    playSongDataList[i].artwork,
                                    playSongDataList[i].originTitle,
                                    playSongDataList[i].originArtistName, playSongDataList[i].coverArtistName, songArr))
                        }
                    }else{
                        Log.e("pass","status != pass")
                        for(i in playSongDataList.indices) {
                            dataListPassing.add(
                                    SongData("","",
                                            playSongDataList[i].artwork,
                                            playSongDataList[i].originTitle, "",
                                            playSongDataList[i].coverArtistName,songArr))
                        }
                    }
                }
            }
        })

        if(status == "pass"){
            scoringPassedRecyclerViewAdapter = ScoringPassedRecyclerViewAdapter(context!!, dataListPassed)
            rv_scoring_song_passed.adapter = scoringPassedRecyclerViewAdapter
            rv_scoring_song_passed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
        }else if(status == "wait")
        {
            scoringPassingRecyclerViewAdapter = ScoringPassingRecyclerViewAdapter(context!!, dataListPassing)
            rv_scoring_song_passing.adapter = scoringPassingRecyclerViewAdapter
            rv_scoring_song_passing.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
        }else
        {
            scoringFailedRecyclerViewAdapter = ScoringFailedRecyclerViewAdapter(context!!, dataListFailed)
            rv_scoring_song_failed.adapter = scoringFailedRecyclerViewAdapter
            rv_scoring_song_failed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
        }
    }




    fun getRatedResponse(){

        val getRatedResponsePass = networkService.getRatedResponse("application/json",authorization_info,"pass")
        val getRatedResponseWait = networkService.getRatedResponse("application/json",authorization_info,"wait")
        val getRatedResponseFail = networkService.getRatedResponse("application/json",authorization_info,"fail")

        getRatedResponsePass.enqueue(object : retrofit2.Callback<GetRecommendResponse>{
            override fun onFailure(call: Call<GetRecommendResponse>, t: Throwable) {
                Log.e("home recommend song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetRecommendResponse>, response: Response<GetRecommendResponse>) {
                if (response.isSuccessful) {
                    val playSongDataList: ArrayList<PlaySongData> = response.body()!!.data

                    if(playSongDataList == null)
                        return

                    for(i in playSongDataList.indices) {
                        dataListPassed.add(
                            SongData("","",
                                playSongDataList[i].artwork,
                                playSongDataList[i].originTitle, "",
                                playSongDataList[i].coverArtistName,songArr)
                        )
                    }
                    scoringPassedRecyclerViewAdapter = ScoringPassedRecyclerViewAdapter(context!!, dataListPassed)
                    rv_scoring_song_passed.adapter = scoringPassedRecyclerViewAdapter
                    rv_scoring_song_passed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
                }
            }
        })

        getRatedResponseWait.enqueue(object : retrofit2.Callback<GetRecommendResponse>{
            override fun onFailure(call: Call<GetRecommendResponse>, t: Throwable) {
                Log.e("home recommend song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetRecommendResponse>, response: Response<GetRecommendResponse>) {
                if (response.isSuccessful) {
                    val playSongDataList: ArrayList<PlaySongData> = response.body()!!.data

                    if(playSongDataList == null)
                        return

                    for(i in playSongDataList.indices) {
                        dataListPassing.add(
                                SongData("","",
                                        playSongDataList[i].artwork,
                                        playSongDataList[i].originTitle, "",
                                        playSongDataList[i].coverArtistName,songArr)
                        )
                    }
                    scoringPassingRecyclerViewAdapter = ScoringPassingRecyclerViewAdapter(context!!, dataListPassing)
                    rv_scoring_song_passing.adapter = scoringPassingRecyclerViewAdapter
                    rv_scoring_song_passing.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
                }
            }
        })

        getRatedResponseFail.enqueue(object : retrofit2.Callback<GetRecommendResponse>{
            override fun onFailure(call: Call<GetRecommendResponse>, t: Throwable) {
                Log.e("home recommend song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetRecommendResponse>, response: Response<GetRecommendResponse>) {
                if (response.isSuccessful) {
                    val playSongDataList: ArrayList<PlaySongData> = response.body()!!.data

                    if(playSongDataList == null)
                        return

                    for(i in playSongDataList.indices) {
                        dataListFailed.add(
                                SongData("","",
                                        playSongDataList[i].artwork,
                                        playSongDataList[i].originTitle, "",
                                        playSongDataList[i].coverArtistName,songArr)
                        )
                    }
                    scoringFailedRecyclerViewAdapter = ScoringFailedRecyclerViewAdapter(context!!, dataListFailed)
                    rv_scoring_song_failed.adapter = scoringFailedRecyclerViewAdapter
                    rv_scoring_song_failed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
                }
            }
        })
    }
}