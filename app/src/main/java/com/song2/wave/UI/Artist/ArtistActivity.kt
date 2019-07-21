package com.song2.wave.UI.Artist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.song2.wave.Data.GET.GetArtistInfoResponse
import com.song2.wave.Data.GET.GetUserInfoResponse
import com.song2.wave.R
import com.song2.wave.UI.Artist.Adapter.FragmentArtistAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.activity_artist.*
import org.jetbrains.anko.ctx
import retrofit2.Call
import retrofit2.Response

class ArtistActivity : AppCompatActivity() {

    val default_profile_img = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwipjMiS-8XjAhWhF6YKHQT1CV0QjRx6BAgBEAU&url=https%3A%2F%2Fwww.kelase.com%2Fassets%2Fmobile%2Fimg%2F&psig=AOvVaw1Dwb6vp_FWXlz0pFqk9JU0&ust=1563796784253163"
    var default_back_img = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwirv6TU-8XjAhXbxosBHTO4CbYQjRx6BAgBEAU&url=https%3A%2F%2Fweheartit.com%2Farticles%2F320533265-music-article-greek-music&psig=AOvVaw2N2PReyR2Q-2IqbARdGIdd&ust=1563796913162882"

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        var userID = intent.getLongExtra("userId",-1)

        getArtistInfoRespnose(userID)

        configureTopNavigation()

    }

    fun getArtistInfoRespnose(userID : Long){
        val getArtistInfoRespnose = networkService.getArtistInfoRespnose("application/json",userID)

        getArtistInfoRespnose.enqueue(object : retrofit2.Callback<GetArtistInfoResponse>{
            override fun onFailure(call: Call<GetArtistInfoResponse>, t: Throwable) {
                Log.e("ArtistInfo","통신 실패")
            }

            override fun onResponse(call: Call<GetArtistInfoResponse>, response: Response<GetArtistInfoResponse>) {
                if (response.isSuccessful){

                    if(response.body()!!.data.comment == null)
                    {
                        response.body()!!.data.comment = "아티스트 페이지 방문을 환영합니다."
                    }

                    //backImg 디폴트이미지
                    if(response.body()!!.data.backImg == null)
                    {
                        response.body()!!.data.backImg = default_back_img
                    }

                    //profileImg 디폴트이미지
                    if(response.body()!!.data.profileImg == null)
                    {
                        response.body()!!.data.profileImg = default_profile_img
                    }

                    Glide.with(ctx).load(response.body()!!.data.backImg).into(iv_artist_act_back_img)
                    Glide.with(ctx).load(response.body()!!.data.profileImg).into(cv_artist_act_profile_img)
                    tv_artist_act_introduce.setText(response.body()!!.data.comment)
                    tv_actist_nickname.setText(response.body()!!.data.nickname)
                    tv_artist_act_like_cnt.setText(response.body()!!.data.allSongLikeCount.toString())

                }else{
                }
            }

        })
    }


    private fun configureTopNavigation() {
        vp_artist_act_artist_library.adapter = FragmentArtistAdapter(supportFragmentManager,2)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_artist_act_artist_library.setupWithViewPager(vp_artist_act_artist_library)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_artist_library_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_artist_act_artist_library.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.rl_artist_library_tabbar_like) as RelativeLayout
        tl_artist_act_artist_library.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.rl_artist_library_tabbar_playlist) as RelativeLayout
    }

}
