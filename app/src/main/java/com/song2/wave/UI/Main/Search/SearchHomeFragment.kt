package com.song2.wave.UI.Main.Search

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.Data.model.*
import com.song2.wave.Data.model.SignUp.OriginArtistData
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.UI.Main.Search.Adapter.CoverArtistSearchAdapter
import com.song2.wave.UI.Main.Search.Adapter.OriginArtistSearchAdapter
import com.song2.wave.UI.Main.Search.Adapter.SearchDataHistoryAdapter
import com.song2.wave.UI.Main.Search.Adapter.SongSearchAdapter
import com.song2.wave.Util.Interface.OnBackPressedListener
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_search_home.*
import kotlinx.android.synthetic.main.fragment_search_home.view.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Response
import android.view.inputmethod.EditorInfo
import com.song2.wave.Data.model.Scoring.TitleData
import com.song2.wave.Util.DB.DBSearchHelper
import org.jetbrains.anko.support.v4.ctx


class SearchHomeFragment : Fragment(), OnBackPressedListener {

    val networkService: NetworkService by lazy {
        ApiClient.getRetrofit().create(NetworkService::class.java)
    }
    var TAG = "SearchFragment"

    lateinit var originDataArr: ArrayList<OriginArtistData>
    lateinit var songDataArr: ArrayList<SongData>
    lateinit var coverArtistDataArr: ArrayList<CoverArtistData>
    lateinit var songFieldData: ArrayList<String?>

    lateinit var originArtistSearchAdapter: OriginArtistSearchAdapter
    lateinit var songSearchAdapter: SongSearchAdapter
    lateinit var coverArtistAdapter: CoverArtistSearchAdapter

    lateinit var searchData: ArrayList<String>
    lateinit var searchDataHistoryAdapter : SearchDataHistoryAdapter
    lateinit var requestManager: RequestManager
    var searchBackFlag: Int = 0 // editText 비활성화 : 0

    lateinit var cursor : Cursor


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_search_home, container, false)

        var searchDbHelper = DBSearchHelper(context)
        var searchDB : SQLiteDatabase = searchDbHelper.writableDatabase

        originDataArr = ArrayList<OriginArtistData>()

        //resent_search
        //호출 될 때 마다
        insertSearchHistoryData(searchDB, v)

        //keyboard - searchBtn

        //ll_search_home_frag_recent_search_setting_bar
        v.edit_search_home_frag_searchbar.setOnEditorActionListener({ textView, actionId, keyEvent ->

            var handled = false

            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                //perform search
                getSearchResponse(edit_search_home_frag_searchbar.text.toString())

                ll_search_home_frag_focus_off.visibility = View.VISIBLE
                ll_search_home_frag_focus_on.visibility = View.GONE
                rv_search_background.visibility = View.GONE

                edit_search_home_frag_searchbar.clearFocus()
                searchBackFlag = 0

                //insert keyword to resent search db
                insertKeyword(edit_search_home_frag_searchbar.text.toString(),searchDbHelper)

            }
            handled

        })

        v.ll_search_home_frag_focus_on.visibility = View.GONE
        songDataArr = ArrayList<SongData>()
        coverArtistDataArr = ArrayList<CoverArtistData>()
        requestManager = Glide.with(this)

        // Edittext focus off
/*        v.ll_search_home_frag_focus.setOnClickListener {
            searchEditTextFocusOff()
            //rv_search_background.visibility = View.VISIBLE

        }*/

        // Edittext focus ON
        v.edit_search_home_frag_searchbar.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
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
            v.rv_search_background.visibility = View.VISIBLE
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this, 2)
    }

    override fun onBackPressed() {
        Log.v(TAG, "검색 창에서 뒤로가기 버튼 실행")
        // 한번 뒤로가기 버튼을 누르면 Listener 를 null, flag = 0 으로 해제
        MainActivity.mainActivity.setOnBackPressedListener(null, 0)
        // editText 활성화일 경우
        if (searchBackFlag == 1) {
            searchEditTextFocusOff()
        }
    }

    // 수정 : ?;
    fun searchEditTextFocusOff() {
        ll_search_home_frag_focus_off.visibility = View.VISIBLE
        ll_search_home_frag_focus_on.visibility = View.GONE
        edit_search_home_frag_searchbar.clearFocus()
        searchBackFlag = 0
    }

    // When searching,
    fun searchEditTextFocusOn() {
        rv_search_background.visibility = View.GONE
        //ll_search_home_frag_focus_off.visibility = View.GONE //최근검색어 보여줄 때 퍌요함..

        //최근검색
        recycler_search_home_frag_search_home_hisory.visibility = View.VISIBLE
        ll_search_home_frag_recent_search_setting_bar.visibility = View.VISIBLE

        ll_search_home_frag_focus_on.visibility = View.VISIBLE
        searchBackFlag = 1
    }

    fun insertSearchHistoryData(searchDB : SQLiteDatabase, view: View) {

        cursor = searchDB.rawQuery("SELECT * FROM SEARCH;", null)

        searchData = ArrayList<String>()

        while(cursor.moveToNext()){
            searchData.add(cursor.getString(1))
            Log.v("searchData",searchData.toString())
        }

        //return

        //최근 검색어 없을 경우
        if(cursor.count.equals(0)){
            return
        }

        searchDataHistoryAdapter = SearchDataHistoryAdapter(searchData)
        searchDataHistoryAdapter.notifyDataSetChanged()
        view.recycler_search_home_frag_search_home_hisory.adapter = searchDataHistoryAdapter
        view.recycler_search_home_frag_search_home_hisory.layoutManager = LinearLayoutManager(context)
        view.recycler_search_home_frag_search_home_hisory.isNestedScrollingEnabled = false
    }

    fun insertKeyword(keyword : String, searchDbHelper : DBSearchHelper){

        searchDbHelper.insert(keyword)

    }

    fun getSearchResponse(searchData: String?) {

        var getSearchResponse = networkService.getSearchResponse("application/json", searchData, searchData, searchData)

        getSearchResponse.enqueue(object : retrofit2.Callback<GetSearchResponse> {
            override fun onFailure(call: Call<GetSearchResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GetSearchResponse>, response: Response<GetSearchResponse>) {

                toast("성공")
                Log.v("Asdf", "성공")

                lateinit var originArtistDataList : ArrayList<OriginArtistData>
                lateinit var originTitleDataList: ArrayList<TitleData>
                lateinit var coverArtistDataList:ArrayList<SearchCoverArtistData>

                //원곡아티스트
                if(response.body()!!.data!!.originArtistName != null) {


                    recycler_search_home_frag_origin_artist.visibility =View.VISIBLE
                    tv_search_home_frag_artist_result_null.visibility= View.GONE


                    originArtistDataList = response.body()!!.data!!.originArtistName!!

                    originDataArr.clear()
                    for (i in originArtistDataList.indices) {
                        originDataArr.add(
                            OriginArtistData(
                                originArtistDataList[i].originArtistIdx,
                                originArtistDataList[i].originArtistName,
                                originArtistDataList[i].originArtistImg
                            )
                        )
                    }

                    originArtistSearchAdapter = OriginArtistSearchAdapter(originDataArr, requestManager)
                    originArtistSearchAdapter.notifyDataSetChanged()
                    recycler_search_home_frag_origin_artist.adapter = originArtistSearchAdapter
                    recycler_search_home_frag_origin_artist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recycler_search_home_frag_origin_artist.isNestedScrollingEnabled = false

                }else{
                    //검색결과 없음
                    //recyclerview 가 안보이게 할 것

                    recycler_search_home_frag_origin_artist.visibility =View.GONE
                    tv_search_home_frag_artist_result_null.visibility= View.VISIBLE
                }


                //원곡타이틀
                if(response.body()!!.data!!.originTitle != null) {

                    recycler_search_home_frag_song.visibility =View.VISIBLE
                    tv_search_home_frag_song_result_null.visibility= View.GONE
                    btn_search_home_frag_song_more.visibility=View.VISIBLE

                    originTitleDataList = response.body()!!.data!!.originTitle!!

                    songDataArr.clear()

                    for (i in originTitleDataList.indices) {
                        songDataArr.add(
                            SongData(
                                originTitleDataList[i].id,
                                originTitleDataList[i].songUrl,
                                originTitleDataList[i].artwork,
                                originTitleDataList[i].originTitle,
                                originTitleDataList[i].originArtistName,
                                originTitleDataList[i].coverArtistName,
                                originTitleDataList[i].genre
                            )
                        )
                    }

                    songSearchAdapter = SongSearchAdapter(songDataArr, requestManager)
                    songSearchAdapter.notifyDataSetChanged()
                    recycler_search_home_frag_song.adapter = songSearchAdapter
                    recycler_search_home_frag_song.layoutManager = LinearLayoutManager(context)
                    recycler_search_home_frag_song.isNestedScrollingEnabled = false

                }else{
                    //검색결과 없음
                    //recyclerview 가 안보이게 할 것
                    recycler_search_home_frag_song.visibility =View.GONE
                    tv_search_home_frag_song_result_null.visibility= View.VISIBLE
                    btn_search_home_frag_song_more.visibility=View.GONE
                }

                //커버가수
                if(response.body()!!.data!!.artistName != null) {

                    recycler_search_home_frag_artist.visibility =View.VISIBLE
                    tv_search_home_frag_cover_artist_result_null.visibility= View.GONE

                    coverArtistDataList = response.body()!!.data!!.artistName!!

                    coverArtistDataArr.clear()

                    for (i in coverArtistDataList.indices) {
                        coverArtistDataArr.add(
                            CoverArtistData(
                                coverArtistDataList[i].userIdx,
                                coverArtistDataList[i].profileImg,
                                coverArtistDataList[i].nickname
                            )
                        )
                        Log.v("userIdx",coverArtistDataList[i].userIdx.toString())
                    }

                    coverArtistAdapter = CoverArtistSearchAdapter(ctx, coverArtistDataArr, requestManager)
                    coverArtistAdapter.notifyDataSetChanged()
                    recycler_search_home_frag_artist.adapter = coverArtistAdapter
                    recycler_search_home_frag_artist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recycler_search_home_frag_artist.isNestedScrollingEnabled = false

                    Log.v("SearchHomeFragment_searchResult : ","coverArtistDataList="+response.body()!!.data!!.artistName.toString())
                }else{
                    //검색결과 없음
                    //recyclerview 가 안보이게 할 것
                    recycler_search_home_frag_artist.visibility =View.GONE
                    tv_search_home_frag_cover_artist_result_null.visibility= View.VISIBLE

                }
            }
        })
    }

}