package com.song2.wave.UI.NetworkTest

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
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.Data.model.*
import com.song2.wave.R
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

class NetworkTestActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_button1.setOnClickListener {
            editContext = main_editText1!!.text.toString()
            val intent = Intent(applicationContext, Main2Activity::class.java)
            intent.putExtra("mainText", editContext)
            startActivity(intent)
        }

        main_button2.setOnClickListener {
            switchContext = main_editText2!!.text.toString()
            when (switchContext) {
                "1" -> {
                    val intent = Intent(applicationContext, Image1Activity::class.java)
                    startActivity(intent)
                }
                "2" -> {
                    val intent = Intent(applicationContext, Image2Activity::class.java)
                    startActivity(intent)
                }
                "3" -> {
                    val intent = Intent(applicationContext, Image3Activity::class.java)
                    startActivity(intent)
                }
                else -> {
                    val intent = Intent(applicationContext, Image4Activity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}