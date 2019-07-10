package com.song2.wave.UI.Main.Search


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.CoverArtistData
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.UI.Main.Search.Adapter.CoverArtistSearchAdapter
import com.song2.wave.UI.Main.Search.Adapter.SearchDataHistoryAdapter
import com.song2.wave.UI.Main.Search.Adapter.SongSearchAdapter
import com.song2.wave.Util.Interface.OnBackPressedListener
import kotlinx.android.synthetic.main.fragment_search_home.*
import kotlinx.android.synthetic.main.fragment_search_home.view.*

class SearchHomeFragment : Fragment(), OnBackPressedListener{

    var TAG = "SearchFragment"

    override fun onBackPressed() {
        Log.v(TAG, "검색 창에서 뒤로가기 버튼 실행")
        // 한번 뒤로가기 버튼을 누르면 Listener 를 null, flag = 0 으로 해제
        MainActivity.mainActivity.setOnBackPressedListener(null, 0)
        // editText 활성화일 경우
        if(searchBackFlag == 1){
            searchEditTextFocusOff()
        }
    }

    lateinit var songDataArr : ArrayList<SongData>
    lateinit var coverArtistDataArr : ArrayList<CoverArtistData>
    lateinit var songFieldData : ArrayList<String?>

    lateinit var songSearchAdapter : SongSearchAdapter
    lateinit var coverArtistAdapter: CoverArtistSearchAdapter

    lateinit var searchData : ArrayList<String>
    lateinit var searchDataHistoryAdapter: SearchDataHistoryAdapter
    lateinit var requestManager : RequestManager
    var searchBackFlag : Int = 0 // editText 비활성화 : 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v : View = inflater.inflate(R.layout.fragment_search_home, container,false)

        v.ll_search_home_frag_focus_on.visibility = View.GONE
        songDataArr = ArrayList<SongData>()
        coverArtistDataArr = ArrayList<CoverArtistData>()
        requestManager = Glide.with(this)

        // Edittext focus off
        v.ll_search_home_frag_focus.setOnClickListener {
            searchEditTextFocusOff()
        }

        insertExampleData()

        songSearchAdapter = SongSearchAdapter(songDataArr, requestManager)
        v.recycler_search_home_frag_song.adapter = songSearchAdapter
        v.recycler_search_home_frag_song.layoutManager = LinearLayoutManager(v.context)
        v.recycler_search_home_frag_song.isNestedScrollingEnabled = false

        coverArtistAdapter = CoverArtistSearchAdapter(coverArtistDataArr, requestManager)
        v.recycler_search_home_frag_artist.adapter = coverArtistAdapter
        v.recycler_search_home_frag_artist.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, false)
        v.recycler_search_home_frag_artist .isNestedScrollingEnabled = false

        insertSearchHistoryData(v)

        // Edittext focus ON
        v.edit_search_home_frag_searchbar.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                searchEditTextFocusOn()
            }
        }

        // "all delte" button is clicked
        v.tv_search_home_frag_all_delete.setOnClickListener {
            searchData.clear()
            searchDataHistoryAdapter = SearchDataHistoryAdapter(searchData)
            v.recycler_search_home_frag_search_home_hisory.adapter = searchDataHistoryAdapter
            v.recycler_search_home_frag_search_home_hisory.layoutManager = LinearLayoutManager(context)
            v.recycler_search_home_frag_search_home_hisory.isNestedScrollingEnabled = false
        }

        // artist is clicked
        v.rel_search_home_frag_artist.setOnClickListener {
            SearchFragment.searchFragment.replaceFragment(SearchArtistFragment())
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun insertExampleData(){
        songFieldData = ArrayList<String?>()
        songFieldData.add("분야1")
        songDataArr.add(SongData("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("data:image/jpeg;base64,K9z4dklLE0/DKH3MOorln2gfzjf0p9KUro6h9pllfaVulKVxHMKUpQClKUApSlAf/Z", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))


        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))

    }

    fun insertSearchHistoryData(v : View){
        searchData = ArrayList<String>()
        searchData.add("example Data1")
        searchData.add("example Data2")
        searchData.add("example Data3")
        searchData.add("example Data4")
        searchData.add("example Data5")

        searchDataHistoryAdapter = SearchDataHistoryAdapter(searchData)
        v.recycler_search_home_frag_search_home_hisory.adapter = searchDataHistoryAdapter
        v.recycler_search_home_frag_search_home_hisory.layoutManager = LinearLayoutManager(context)
        v.recycler_search_home_frag_search_home_hisory.isNestedScrollingEnabled = false
    }

    fun searchEditTextFocusOff(){
        ll_search_home_frag_focus_off.visibility = View.VISIBLE
        ll_search_home_frag_focus_on.visibility = View.GONE
        edit_search_home_frag_searchbar.clearFocus()
        searchBackFlag = 0
    }

    fun searchEditTextFocusOn(){
        ll_search_home_frag_focus_off.visibility = View.GONE
        ll_search_home_frag_focus_on.visibility = View.VISIBLE
        searchBackFlag = 1
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this, 2)
    }

}