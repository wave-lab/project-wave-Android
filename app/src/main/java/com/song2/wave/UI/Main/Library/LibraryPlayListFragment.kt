package com.song2.wave.UI.Main.Library

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
import com.song2.wave.Data.model.SongData
import com.song2.wave.UI.Main.Library.Adapter.LikeListAdapter
import com.song2.wave.Util.DB.DBHelper
import kotlinx.android.synthetic.main.fragment_library_playlist.view.*

class LibraryPlayListFragment : Fragment() {

    lateinit var songDataArr : ArrayList<SongData>
    lateinit var requestManager : RequestManager
    lateinit var songFieldData : ArrayList<String?>
    lateinit var sqlDB : SQLiteDatabase
    lateinit var dbHelper: DBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_library_playlist, container, false)
        songDataArr = ArrayList<SongData>()
        requestManager = Glide.with(this)
        val dbHelper = DBHelper(context)

        sqlDB = dbHelper.readableDatabase
        var cursor : Cursor
        cursor = sqlDB.rawQuery("SELECT * FROM SONG;", null)

        var songArr = ArrayList<String?>()
        songArr.add("m1")
        while(cursor.moveToNext()){
            Log.v("asdf","이미지 = " + cursor.getString(6))
            songDataArr.add(SongData(cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getString(5),cursor.getString(3), cursor.getString(4),songArr ))
        }

      //  insertData(v)
        v.recycler_library_playlist_frag_list.adapter = LikeListAdapter(context!!, songDataArr, requestManager)
        v.recycler_library_playlist_frag_list.layoutManager = LinearLayoutManager(v.context)

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun insertData(v : View){

        songFieldData = ArrayList<String?>()
        songFieldData.add("분야1")

        songDataArr.add(SongData("1","sadf","https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("1","sadf","http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("1","sadf","data:image/jpeg;base64,K9z4dklLE0/DKH3MOorln2gfzjf0p9KUro6h9pllfaVulKVxHMKUpQClKUApSlAf/Z", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("1","sadf","https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("1","sadf","https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))


    }
}