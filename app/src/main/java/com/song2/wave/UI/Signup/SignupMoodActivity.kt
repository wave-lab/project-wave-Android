package com.song2.wave.UI.Signup

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.song2.wave.R
import com.song2.wave.R.id.img_signup_mood_act_funny
import com.song2.wave.Util.Network.ApiClient
import com.song2.wave.Util.Network.NetworkService
import com.song2.wave.Util.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_signup_mood.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class SignupMoodActivity : AppCompatActivity(), View.OnClickListener {


    private var image : MultipartBody.Part? = null
    lateinit var receivedImgUri : Uri
    lateinit var networkService : NetworkService
    lateinit var selectedMoodArr :  ArrayList<String>

    lateinit var moodArr : Array<ImageView>

    override fun onClick(v: View?) {
        for(i in 0..7){
            if (v!!.id == moodArr[0].getId()) {
                selectedMoodArr.add("m" + ((i+1).toString()))
                Toast.makeText(applicationContext, "분위기 " + "m" + (i+1).toString() + "번 버튼 선택", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_mood)

        selectedMoodArr = ArrayList<String>()

        var funnyImg :ImageView = findViewById(R.id.img_signup_mood_act_funny)
        var fluttetImg :ImageView = findViewById(R.id.img_signup_mood_act_flutter)
        var hipImg :ImageView = findViewById(R.id.img_signup_mood_act_hip)
        var sadImg :ImageView = findViewById(R.id.img_signup_mood_act_sad)
        var windliessImg :ImageView = findViewById(R.id.img_signup_mood_act_windless)
        var dreamyImg :ImageView = findViewById(R.id.img_signup_mood_act_dreamy)
        var groovyImg :ImageView = findViewById(R.id.img_signup_mood_act_windless)
        var honeyImg :ImageView = findViewById(R.id.img_signup_mood_act_honey)


        moodArr = arrayOf<ImageView>(funnyImg, honeyImg, fluttetImg, sadImg, hipImg, windliessImg, groovyImg, dreamyImg)

        for(i in 0..7){
            moodArr[i].setOnClickListener(this)
        }

        receivedImgUri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri

        btn_signup_mood_next.setOnClickListener {
            postSignup()
        }
        handleImage()

    }

    fun handleImage(){
        val options = BitmapFactory.Options()

        var input: InputStream? = null // here, you need to get your context.
        try {
            input = contentResolver.openInputStream(receivedImgUri)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
        val img = File(getRealPathFromURI(applicationContext,receivedImgUri).toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

        image = MultipartBody.Part.createFormData("image", img.name, photoBody)
    }

    // 이미지 파일을 확장자까지 표시해주는 메소드
    fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }

    fun postSignup(){
        val pref = applicationContext.getSharedPreferences("auto", Activity.MODE_PRIVATE)
        var emailValue : String = pref.getString("email","")
        var passwordValue : String = pref.getString("password","")
        var nicknameValue : String = pref.getString("nickname","")

        Log.v("SignupMoodActivity", "회원가입 이메일 = "+emailValue)
        Log.v("SignupMoodActivity", "회원가입 패스워드 = "+passwordValue)
        Log.v("SignupMoodActivity", "회원가입 닉네임 = "+nicknameValue)
        Log.v("SignupMoodActivity", "회원가입 이미지 파일 " + image)

        networkService = ApiClient.getRetrofit().create(NetworkService::class.java)

        val email = RequestBody.create(MediaType.parse("text.plain"), emailValue)
        val password = RequestBody.create(MediaType.parse("text.plain"), passwordValue)
        val nickname = RequestBody.create(MediaType.parse("text.plain"),nicknameValue )
        val genre0 = RequestBody.create(MediaType.parse("text.plain"),"g2" )
        val genre1 = RequestBody.create(MediaType.parse("text.plain"),"g3" )
        val genre2 = RequestBody.create(MediaType.parse("text.plain"),"g3" )
        val mood0 = RequestBody.create(MediaType.parse("text.plain"),"m1" )
        val mood1 = RequestBody.create(MediaType.parse("text.plain"),"m2" )
        val originArtist = RequestBody.create(MediaType.parse("text.plain"), "3")

        val postRoomTestResponse = networkService.postSignup(email, password,nickname,image,genre0,genre1, genre2,mood0,mood1,originArtist)
        postRoomTestResponse.enqueue(object : retrofit2.Callback<PostResponse>{

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.success == true){
                        Toast.makeText(applicationContext, "회원가입 완료", Toast.LENGTH_SHORT).show()
                        Log.v("SignupMoodActivity","값 전달 성공")
                    }
                    else{
                        Log.v("SignupMoodActivity","값 전달 실패")
                    }
                }
                else{
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
            }
        })

    }

}
