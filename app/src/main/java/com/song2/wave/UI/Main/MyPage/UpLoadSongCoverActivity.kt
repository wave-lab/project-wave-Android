package com.song2.wave.UI.Main.MyPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_up_load_song_cover.*

class UpLoadSongCoverActivity : AppCompatActivity() {


    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    lateinit var selectedPicUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R
            .layout.activity_up_load_song_cover)

        ll_upload_cover_act_upload_artwork.setOnClickListener {
            uploadImg()
        }
    }

    fun uploadImg() {

        var intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    Log.e("TestAudioData", data.toString())
                    Log.v("UploadActivity", "실제 경로 = " + getRealPathFromURI(applicationContext, it.data).toString())
                    selectedPicUri = it.data
                    Glide.with(this).load(selectedPicUri)
                        .thumbnail(0.1f).into(iv_upload_cover_file_act_thumb)
                }
            }
        }

/*        if (requestCode == REQUEST_CODE_SELECT_AUDIO) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    selectedAudioUri = it.data
                    var mediaPlayer = MediaPlayer()

                    Log.e("error selectedAudioUri", selectedAudioUri.toString())
                    Log.v("UploadActivity", "음악 실제 경로 = " + getRealPathFromURI(applicationContext, it.data).toString())

                    //var song = selectedAudioUri.toString() + ".mp3"
                    //mediaPlayer.setDataSource(song)
                    //mediaPlayer.prepare()
                    //mediaPlayer.start()

                }
            }
        }*/
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
}
