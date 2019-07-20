package com.song2.wave.UI.Main.MyPage.UpLoadSong

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.Util.Network.ApiClientSec
import com.song2.wave.Util.Network.NetworkService
import com.song2.wave.Util.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_upload_mood.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Response
import java.io.*

class UpLoadMoodActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var authorization_info: String
    val networkService: NetworkService by lazy {
        ApiClientSec.getRetrofit().create(NetworkService::class.java)
    }


    lateinit var data: Uri

    lateinit var selectedArtistArr: ArrayList<String>

    var genre = ArrayList<RequestBody?>()
    var mood = ArrayList<RequestBody?>()
    var originArtist = ArrayList<RequestBody?>()
    private var coverImg: MultipartBody.Part? = null
    private var audioFile: MultipartBody.Part? = null

    lateinit var receivedImgUri: Uri
    lateinit var receivedAudioUri: Uri

    lateinit var selectedMoodArr: ArrayList<String>
    lateinit var selectedGenreArr: ArrayList<String>

    lateinit var moodArr: Array<ImageView>

    override fun onClick(v: View?) {
        for (i in 0..7) {
            if (v!!.id == moodArr[i].getId()) {
                if (moodArr[i].isSelected) {
                    selectedMoodArr.remove(("m" + (i + 1)))
                } else {
                    selectedMoodArr.add(("m" + (i + 1)))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_mood)


        var pref = this!!.getSharedPreferences("auto", Activity.MODE_PRIVATE)
        //authorization_info = pref.getString("token", "")
        authorization_info = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxNiwiaWF0IjoxNTYyOTY3NzY2LCJleHAiOjE1NjU1NTk3NjZ9.PmlhTASv3yT75I_RG9T6YRL-BdCAGZaE7fpB4r_G3BM"

        selectedMoodArr = ArrayList<String>()
        selectedGenreArr = ArrayList<String>()
        selectedArtistArr = ArrayList<String>()

        var funnyImg: ImageView = findViewById(R.id.img_upload_mood_act_funny)
        var fluttetImg: ImageView = findViewById(R.id.img_upload_mood_act_flutter)
        var hipImg: ImageView = findViewById(R.id.img_upload_mood_act_hip)
        var sadImg: ImageView = findViewById(R.id.img_upload_mood_act_sad)
        var windliessImg: ImageView = findViewById(R.id.img_upload_mood_act_windless)
        var dreamyImg: ImageView = findViewById(R.id.img_upload_mood_act_dreamy)
        var groovyImg: ImageView = findViewById(R.id.img_upload_mood_act_windless)
        var honeyImg: ImageView = findViewById(R.id.img_upload_mood_act_honey)


        moodArr = arrayOf<ImageView>(funnyImg, honeyImg, fluttetImg, sadImg, hipImg, windliessImg, groovyImg, dreamyImg)

        for (i in 0..7) {
            moodArr[i].setOnClickListener(this)
        }

        btn_upload_mood_next.setOnClickListener {
            postSongUploadResponse()
        }
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

    fun postSongUploadResponse() {

        var originTitleValue: String = intent.getStringExtra("Title")
        var originArtistNameValue: String = intent.getStringExtra("Artist")
        var songCommentValue: String = intent.getStringExtra("Comment")
        var highlightTimeValue: String = intent.getStringExtra("StartPoint")

        var genreValue: ArrayList<String> = intent.getStringArrayListExtra("genre")

        Log.v("UploadActivity", "originTitle = " + originTitleValue)
        Log.v("UploadActivity", "originArtistName = " + originArtistNameValue)
        Log.v("UploadActivity", "songComment " + songCommentValue)
        Log.v("UploadActivity", "songHigilight " + highlightTimeValue)

        //장르&무드 형변환
        for (i in genreValue.indices) {
            genre.add(RequestBody.create(MediaType.parse("text.plain"), genreValue[i]))
            Log.v("UploadMoodAct","GenreValue "+i+" : "+genreValue[i])
        }

        for (i in selectedMoodArr.indices) {
            mood.add(RequestBody.create(MediaType.parse("text.plain"), selectedMoodArr[i]))
            Log.v("UploadMoodAct","selectedMoodArr "+i+" : "+selectedMoodArr[i])

        }

        val title = RequestBody.create(MediaType.parse("text.plain"), originTitleValue)
        val originArtistName = RequestBody.create(MediaType.parse("text.plain"), originArtistNameValue)
        val songComment = RequestBody.create(MediaType.parse("text.plain"), songCommentValue)
        val highlightTime = RequestBody.create(MediaType.parse("text.plain"), highlightTimeValue)


        /// 이미지 파일변환
        receivedImgUri = Uri.parse(intent.getStringExtra("picURI"))
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
        val img = File(getRealPathFromURI(applicationContext, receivedImgUri).toString()) // 가져온 파일의 이름을 알아내려고 사용합니다
        coverImg = MultipartBody.Part.createFormData("artwork", img.name, photoBody)

        /// mp3 파일변환
        val audio = File(getRealPathFromURI(applicationContext, receivedImgUri)) // 가져온 파일의 이름을 알아내려고 사용합니다
        val audioBody = RequestBody.create(MediaType.parse("audio/*"), audio)


        audioFile = MultipartBody.Part.createFormData("songUrl", audio.name, audioBody)

        val postSongUploadResponse = networkService.postSongUploadResponse(
            authorization_info, title,  originArtistName, genre, mood, songComment, highlightTime, coverImg, audioFile)

        postSongUploadResponse.enqueue(object : retrofit2.Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.success == true) {
                        Toast.makeText(applicationContext, "곡업로드 완료", Toast.LENGTH_SHORT).show()
                        Log.e("UploadMoodActivity", "곡업로드 성공")
                        startActivity<MainActivity>()
                    } else {
                        Log.e("UploadMoodActivity", "실패message : " + response.body()!!.message)
                    }
                } else {
                    Log.e("response", response.body().toString())
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.e("UploadMoodActivity", "곡업로드 실패" + t.toString())

            }

        })

    }
}
