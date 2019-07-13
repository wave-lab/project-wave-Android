package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.Data.model.MyPage.PointHistoryData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.PointHistoryViewAdapter
import kotlinx.android.synthetic.main.fragment_point_history.*

class PointHistoryFragment : Fragment() {
    lateinit var point_history_view_adapter : PointHistoryViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_point_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        //임시데이터
        var dataList: ArrayList<PointHistoryData> = ArrayList()
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(true, "나는야","이선",500))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(false, "나는야","이선",100))
        dataList.add(PointHistoryData(true, "나는야","이선",500))

        point_history_view_adapter = PointHistoryViewAdapter(activity!!, dataList)
        rv_fragment_point_history_list.adapter = point_history_view_adapter
        rv_fragment_point_history_list.layoutManager = LinearLayoutManager(activity)
    }

}