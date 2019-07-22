package com.song2.wave.UI.Artist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.GET.GetArtistInfoResponse
import com.song2.wave.Data.GET.GetUserInfoResponse
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Artist.Adapter.FragmentArtistAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringWaitAdapter
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.fragment_scoring_wait.view.*
import org.jetbrains.anko.ctx
import retrofit2.Call
import retrofit2.Response

class ArtistActivity : AppCompatActivity() {


    lateinit var songDataArr : ArrayList<SongData>
    lateinit var requestManager : RequestManager
    lateinit var songFieldData : ArrayList<String?>

    val default_profile_img = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwipjMiS-8XjAhWhF6YKHQT1CV0QjRx6BAgBEAU&url=https%3A%2F%2Fwww.kelase.com%2Fassets%2Fmobile%2Fimg%2F&psig=AOvVaw1Dwb6vp_FWXlz0pFqk9JU0&ust=1563796784253163"
    var default_back_img = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwirv6TU-8XjAhXbxosBHTO4CbYQjRx6BAgBEAU&url=https%3A%2F%2Fweheartit.com%2Farticles%2F320533265-music-article-greek-music&psig=AOvVaw2N2PReyR2Q-2IqbARdGIdd&ust=1563796913162882"

    val networkService: NetworkService by lazy { ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        var userID = intent.getLongExtra("userId",-1)

        insertData()
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

    fun insertData(){

        requestManager = Glide.with(this)

        songFieldData = ArrayList<String?>()
        songFieldData.add("분야1")

        songDataArr = ArrayList()

        songDataArr.add(SongData("er","asdf","https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","data:image/jpeg;base64,K9z4dklLE0/DKH3MOorln2gfzjf0p9KUro6h9pllfaVulKVxHMKUpQClKUApSlAf/Z", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","data:image/jpeg;base64,K9z4dklLE0/DKH3MOorln2gfzjf0p9KUro6h9pllfaVulKVxHMKUpQClKUApSlAf/Z", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("er","asdf","https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))


        rv_artist_act_song_list.adapter = ScoringWaitAdapter(ctx!!, songDataArr, requestManager)
        rv_artist_act_song_list.layoutManager = LinearLayoutManager(ctx)
    }

}
