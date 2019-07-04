package com.song2.wave.UI.Main.Home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.WaitingSongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Adapter.WaitingSongHomeAdapter

class HomeFragment : Fragment() {
    lateinit var dataList: ArrayList<WaitingSongData>
    lateinit var requestManager: RequestManager
    lateinit var waitingSongHomeAdapter: WaitingSongHomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachRecyclerView()


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //(context as MainActivity).setOnBackPressedListener(this, 1)
    }

    fun attachRecyclerView(){

        dataList = ArrayList()

        insertExampleData()

//        waitingSongHomeAdapter = WaitingSongHomeAdapter(dataList, requestManager)

    }

    fun insertExampleData() {

        dataList.add(WaitingSongData(3, "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "노래제목", "가숫"))
        dataList.add(WaitingSongData(3, "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "노래제목", "가숫"))
        dataList.add(WaitingSongData(3, "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "노래제목", "가숫"))

    }
}