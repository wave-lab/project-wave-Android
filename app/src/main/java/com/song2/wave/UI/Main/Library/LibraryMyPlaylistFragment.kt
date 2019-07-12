package com.song2.wave.UI.Main.Library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetCustomPlayListResponse
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.model.CustomPlayListData
import com.song2.wave.Data.model.PlaylistListData
import com.song2.wave.UI.Main.Library.Adapter.MyPlaylistAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.ApplicationController
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_library_my_playlist.*
import kotlinx.android.synthetic.main.fragment_library_my_playlist.view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LibraryMyPlaylistFragment : Fragment() {

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    lateinit var authorization_info : String

    lateinit var songDataArr : ArrayList<PlaylistListData>
    lateinit var requestManager : RequestManager
    lateinit var songFieldData : ArrayList<String?>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_library_my_playlist, container, false)

        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

        songDataArr = ArrayList<PlaylistListData>()
        requestManager = Glide.with(this)

        getCustomPlaylistResponse()
        //insertData(v)

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun insertData(v : View){

/*        songFieldData = ArrayList<String?>()
        songFieldData.add("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934")
        songFieldData.add("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934")
        songFieldData.add("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934")
        songFieldData.add("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934")
        songFieldData.add("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934")

        songDataArr.add(PlaylistListData(songFieldData, "좋은날", "아이유(IU)"))
        songDataArr.add(PlaylistListData(songFieldData, "가을아침", "아이유(IU)"))
        songDataArr.add(PlaylistListData(songFieldData, "Zeze", "아이유(IU)"))
        songDataArr.add(PlaylistListData(songFieldData, "꽃갈피", "아이유(IU)"))
        songDataArr.add(PlaylistListData(songFieldData,  "무릎", "아이유(IU)"))*/


    }

    fun getCustomPlaylistResponse(){
        var getCustomPlayListData = networkService.getCustomPlaylistResponse("application/json",authorization_info)

        getCustomPlayListData.enqueue(object : retrofit2.Callback<GetCustomPlayListResponse>{
            override fun onFailure(call: Call<GetCustomPlayListResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<GetCustomPlayListResponse>,
                response: Response<GetCustomPlayListResponse>
            ) {

                val playlistDataList: ArrayList<CustomPlayListData> = response.body()!!.data


                if(playlistDataList == null)
                    return

                for(i in playlistDataList.indices){
                    songDataArr.add(PlaylistListData(playlistDataList[i].thumbnail, playlistDataList[i].playlistName, playlistDataList[i].playlistComment))

                }

                recycler_library_my_playlist_frag_list.adapter = MyPlaylistAdapter(songDataArr, requestManager)
                recycler_library_my_playlist_frag_list.layoutManager = LinearLayoutManager(context)
            }
        })
    }
}