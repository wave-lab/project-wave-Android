package com.song2.wave.UI.Main.Library


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.UI.Main.Library.Adapter.MyPlaylistAdapter
import kotlinx.android.synthetic.main.fragment_library_my_playlist.view.*

class LibraryMyPlaylistFragment : Fragment() {

    lateinit var songDataArr : ArrayList<SongData>
    lateinit var requestManager : RequestManager
    lateinit var songFieldData : ArrayList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_library_my_playlist, container, false)
        songDataArr = ArrayList<SongData>()
        requestManager = Glide.with(this)
        insertData(v)

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun insertData(v : View){

        songFieldData = ArrayList<String>()
        songFieldData.add("분야1")

        songDataArr.add(SongData("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("data:image/jpeg;base64,K9z4dklLE0/DKH3MOorln2gfzjf0p9KUro6h9pllfaVulKVxHMKUpQClKUApSlAf/Z", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))

        v.recycler_library_my_playlist_frag_list.adapter = MyPlaylistAdapter(songDataArr, requestManager)
        v.recycler_library_my_playlist_frag_list.layoutManager = LinearLayoutManager(v.context)
    }
}