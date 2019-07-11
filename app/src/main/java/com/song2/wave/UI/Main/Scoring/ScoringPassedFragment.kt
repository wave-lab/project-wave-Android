package com.song2.wave.UI.Main.Scoring

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassedListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_scoring_passed_list.*

class ScoringPassedFragment : Fragment() {
    lateinit var songDataArr: ArrayList<SongData>
    lateinit var requestManager: RequestManager
    lateinit var songFieldData: ArrayList<String?>

    lateinit var scoringPassedListRecyclerViewAdapter: ScoringPassedListRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scoring_completed, container, false)
    }

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_scoring_wait, container, false)
        songDataArr = ArrayList<SongData>()
        requestManager = Glide.with(this)
        insertData(v)

        return v*/
//}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var dataListPassedList: ArrayList<SongData> = ArrayList()
        requestManager = Glide.with(this)




        scoringPassedListRecyclerViewAdapter = ScoringPassedListRecyclerViewAdapter(context!!, songDataArr, requestManager)
        rv_scoring_passed_list.adapter = scoringPassedListRecyclerViewAdapter
        rv_scoring_passed_list.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)


    }
}