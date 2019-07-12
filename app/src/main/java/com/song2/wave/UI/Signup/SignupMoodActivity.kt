package com.song2.wave.UI.Signup

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.bumptech.glide.Glide
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.Util.Network.ApiClientSec
import com.song2.wave.Util.Network.POST.PostResponse
import com.song2.wave.Util.Network.SecondNetworkService
import kotlinx.android.synthetic.main.activity_signup_mood.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class SignupMoodActivity : AppCompatActivity(), View.OnClickListener {

    var mood = ArrayList<RequestBody>()
    var REQ_CODE_SELECT_IMAGE = 1001
    val authorization_info =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"
    lateinit var data : Uri

    lateinit var selectedArtistArr : ArrayList<String>

    var genre = ArrayList<RequestBody>()
    var originArtist = ArrayList<RequestBody?>()
    private var profileImg: MultipartBody.Part? = null
    lateinit var receivedImgUri: Uri
    lateinit var selectedMoodArr: ArrayList<String>
    lateinit var selectedGenreArr: ArrayList<String>

    lateinit var moodArr: Array<ImageView>

    override fun onClick(v: View?) {
        for (i in 0..7) {
            if (v!!.id == moodArr[i].getId()) {
                if(moodArr[i].isSelected){
                    selectedMoodArr.remove(("m" + (i+1)))
                }else{
                    selectedMoodArr.add(("m" + (i+1)))
                }
//                Toast.makeText(applicationContext, "분위기 " + "m" + (i + 1).toString() + "번 버튼 선택", Toast.LENGTH_LONG).show()
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_mood)

        selectedMoodArr = ArrayList<String>()
        selectedGenreArr = ArrayList<String>()
        selectedArtistArr = ArrayList<String>()

        receivedImgUri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri
        selectedArtistArr = intent.getStringArrayListExtra("selectedArtistArr")
        selectedGenreArr = intent.getStringArrayListExtra("selectedGenreArr")

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

        profileImg = MultipartBody.Part.createFormData("profileImg", img.name, photoBody)

        var funnyImg: ImageView = findViewById(R.id.img_signup_mood_act_funny)
        var fluttetImg: ImageView = findViewById(R.id.img_signup_mood_act_flutter)
        var hipImg: ImageView = findViewById(R.id.img_signup_mood_act_hip)
        var sadImg: ImageView = findViewById(R.id.img_signup_mood_act_sad)
        var windliessImg: ImageView = findViewById(R.id.img_signup_mood_act_windless)
        var dreamyImg: ImageView = findViewById(R.id.img_signup_mood_act_dreamy)
        var groovyImg: ImageView = findViewById(R.id.img_signup_mood_act_windless)
        var honeyImg: ImageView = findViewById(R.id.img_signup_mood_act_honey)


        moodArr = arrayOf<ImageView>(funnyImg, honeyImg, fluttetImg, sadImg, hipImg, windliessImg, groovyImg, dreamyImg)

        for (i in 0..7) {
            moodArr[i].setOnClickListener(this)
        }

        btn_signup_mood_next.setOnClickListener {
            Log.v("FirebaseInstanceIDService", "테스트 실1")
            postSignup()
        }

    }

    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)

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

    fun postSignup() {

        val pref = applicationContext.getSharedPreferences("auto", Activity.MODE_PRIVATE)
        val emailValue = pref.getString("email","")
        val passwordValue = pref.getString("password","")
        val nicknameValue = pref.getString("nickname","")

        for(i in 0..selectedArtistArr.size-1){
            Log.v("asdf", "선택 아티스트 = " + selectedArtistArr.get(i))
            originArtist.add(RequestBody.create(MediaType.parse("text.plain"), selectedArtistArr.get(i)))
        }

        for(i in 0..selectedMoodArr.size-1){
            Log.v("asdf", "선택 분위기 = " + selectedMoodArr.get(i))
            mood.add(RequestBody.create(MediaType.parse("text.plain"), selectedMoodArr.get(i)))
        }
        for(i in 0 .. selectedGenreArr.size-1){
            Log.v("asdf", "선택 장르 = " + selectedGenreArr.get(i))
            genre.add(RequestBody.create(MediaType.parse("text.plain"), selectedGenreArr.get(i)))
        }

        var networkService = ApiClientSec.getRetrofit().create(SecondNetworkService::class.java)

        val email = RequestBody.create(MediaType.parse("text.plain"), emailValue)
        val password = RequestBody.create(MediaType.parse("text.plain"), passwordValue)
        val nickname = RequestBody.create(MediaType.parse("text.plain"), nicknameValue)

        val originArtistValue = RequestBody.create(MediaType.parse("text.plain"), "3")
        var originArtist = ArrayList<RequestBody>()
        originArtist.add(originArtistValue)

    val postRoomTestResponse = networkService.postSignupsResponse(email, password, nickname, profileImg, genre, mood, originArtist!!)
        postRoomTestResponse.enqueue(object : retrofit2.Callback<PostResponse> {

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful) {
                    Log.v("Signup Activity", "회원가입 성공")
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Log.v("Signup Activity", "테스트 성공124")

                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
                Log.v("FirebaseInstanceIDService", "테스트 실패 = "+t!!.toString())

            }
        })
    }



}
