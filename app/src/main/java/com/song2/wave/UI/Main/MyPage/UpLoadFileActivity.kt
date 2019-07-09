package com.song2.wave.UI.Main.MyPage

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_upload_file.*
import org.jetbrains.anko.startActivity

class UpLoadFileActivity : AppCompatActivity() {

    val REQUEST_CODE_SELECT_AUDIO: Int = 2004
    lateinit var selectedAudioUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_file)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE!!), 1)
            }
        }

        rl_upload_act_upload_music.setOnClickListener {
            uploadMusic()
        }
    }


    fun uploadMusic() {

        var intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Audio.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        Log.e("uploadMusic", intent.data.toString())
        Log.v("uploadMusic", intent.data.toString())

        //getMusicList()

        startActivityForResult(intent, REQUEST_CODE_SELECT_AUDIO)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_AUDIO) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    selectedAudioUri = it.data
                    var mediaPlayer = MediaPlayer()

                    //Log.e("error selectedAudioUri", selectedAudioUri.toString())
                    //Log.v("UploadActivity", "음악 실제 경로 = " + getRealPathFromURI(applicationContext, it.data))

                    startActivity<SetStartPointActivity>("musicDataURI" to getRealPathFromURI(applicationContext, it.data) )

                    //var song = selectedAudioUri.toString() + ".mp3"
                    //mediaPlayer.setDataSource(song)
                    //mediaPlayer.prepare()
                    //mediaPlayer.start()

                }
            }
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
}
