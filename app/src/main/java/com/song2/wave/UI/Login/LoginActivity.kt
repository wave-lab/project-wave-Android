package com.song2.wave.UI.Login

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.song2.wave.Data.POST.PostLogin
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import com.song2.wave.Util.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_submit_act_login.setOnClickListener {
            postLogin()
        }
    }

    // 로그인
    fun postLogin()
    {
        val networkService = ApiClient.getRetrofit().create(NetworkService::class.java)
        var postLogin = PostLogin(edt_id_act_email_login.text.toString(), edt_pw_act_email_login.text.toString())
        var postLoginResponse = networkService.postLogin(postLogin)
        postLoginResponse.enqueue(object : retrofit2.Callback<PostResponse>{

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if(response.isSuccessful){
                    Log.v("Asdf","승인 = " + response.body())
                    var token = response.body()!!.data
                    Log.v("ASdf"," 토큰 = " + token)
                    // 자신의 유저 정보 내부 DB에 저장
                    var pref = applicationContext.getSharedPreferences("auto", Activity.MODE_PRIVATE)
                    var editor: SharedPreferences.Editor = pref.edit()
                    editor.putString("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxNiwiaWF0IjoxNTYyOTY3NzY2LCJleHAiOjE1NjU1NTk3NjZ9.PmlhTASv3yT75I_RG9T6YRL-BdCAGZaE7fpB4r_G3BM") // 토란  key값으로 userID 데이터를 저장한다.
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
            }
        })
    }
}
