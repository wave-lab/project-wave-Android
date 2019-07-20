package com.song2.wave.UI.Main.Home.Top10

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetTop10PlaylistResponse
import com.song2.wave.Data.model.Home.Top10ListData
import com.song2.wave.Data.model.PlaySongData
import com.song2.wave.UI.Main.Home.Top10.Adapter.Top10ListAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_top10_idx0.*
import retrofit2.Call
import retrofit2.Response

class Idx0Fragment : Fragment(){


    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    lateinit var authorization_info : String

    lateinit var top10Idx0List: ArrayList<Top10ListData>
    lateinit var top10Idx0ListAdapter: Top10ListAdapter

    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_top10_idx0, container, false)

        return v

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"
        attachRecyclerView()
    }

    fun attachRecyclerView(){

        top10Idx0List = ArrayList()
        requestManager = Glide.with(this)

        //insertData()

/*        Log.e("genreId",getArguments()!!.getString("genreId"))
        if(getArguments()!!.getString("genreId") != null){
            getTop10PlaylistResponse(getArguments()!!.getString("genreId"))
        }*/
        getTop10PlaylistResponse("5d2623a43529312714c5aa33")

    }

    fun insertData(){

        top10Idx0List.add(Top10ListData(2, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx0List.add(Top10ListData(3, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx0List.add(Top10ListData(4, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx0List.add(Top10ListData(5, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx0List.add(Top10ListData(6, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))

    }

    fun getTop10PlaylistResponse(id : String?){
        //val getTop10PlaylistResponse = networkService.getTop10PlaylistResponse("application/json",authorization_info,null)
        val getTop10PlaylistResponse = networkService.getTop10PlaylistResponse("application/json",authorization_info,id)
        getTop10PlaylistResponse.enqueue(object : retrofit2.Callback<GetTop10PlaylistResponse> {
            override fun onFailure(call: Call<GetTop10PlaylistResponse>, t: Throwable) {
                Log.e("idx frag play song list fail", t.toString())
            }

            override fun onResponse(call: Call<GetTop10PlaylistResponse>, response: Response<GetTop10PlaylistResponse>
            ) {
                val top10PlaylistDataList  = response.body()!!.data

                Log.v("aa","respose = " + response.body()!!)


                if(top10PlaylistDataList.songList != null){

                    insert1stSong(top10PlaylistDataList.songList[0])

                    for(i in top10PlaylistDataList.songList.indices) {
                        top10Idx0List.add(Top10ListData(i+1, top10PlaylistDataList.songList[i].artwork, top10PlaylistDataList.songList[i].originTitle, top10PlaylistDataList.songList[i].originArtistName, top10PlaylistDataList.songList[i].coverArtistName, top10PlaylistDataList.songList[i].genre))
                    }

                    top10Idx0ListAdapter = Top10ListAdapter(top10Idx0List, requestManager)
                    rv_top10_idx0_list.adapter = top10Idx0ListAdapter
                    rv_top10_idx0_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }

            }

        })
    }

    fun insert1stSong(firstdataList : PlaySongData){

        Glide.with(this).load(firstdataList.artwork).into(iv_top10_idx0_cover_img)
        tv_top10_idx0_song_info.setText(firstdataList.originTitle + " - "+firstdataList.originArtistName)
        tv_top10_idx0_cover_artist.setText(firstdataList.coverArtistName)

        var genre : String = ""
        for(i in firstdataList.genre.indices){
            genre = genre + " "+i
        }
        tv_top10_idx0_genre.setText(genre)

    }
}