package com.song2.wave.UI.Main.MyPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_set_start_point.*
import kotlinx.android.synthetic.main.activity_up_load_song_cover.*
import org.jetbrains.anko.startActivity

class UpLoadSongCoverActivity : AppCompatActivity() {
    var mediaPlayer = MediaPlayer()

    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    lateinit var selectedPicUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_load_song_cover)

        var upload_song_uri: String = intent.getStringExtra("songURI")
        var start_point : Int = intent.getStringExtra("StartPoint").toInt()

        mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(upload_song_uri))
        mediaPlayer.prepare()

        var play_duration = mediaPlayer.getDuration()

        var startPointOfSong = String.format("%02d:%02d", ((start_point / 1000) % 3600 / 60), ((start_point / 1000) % 3600 % 60))
        var lengthOfSong = String.format("%02d:%02d", ((play_duration / 1000) % 3600 / 60), ((play_duration / 1000) % 3600 % 60))


        //사진업로드
        ll_upload_cover_act_upload_artwork.setOnClickListener {
            uploadImg()
        }

        //하이라이트
        tv_up_load_cover_act_start_point.setText(startPointOfSong+"초 부터")
        tv_up_load_cover_act_duration_time.setText(startPointOfSong)
        tv_up_load_cover_act_length_of_song.setText(lengthOfSong)
        var seekbar: SeekBar = sb_up_load_cover_act_seekbar
        seekbar.setMax(play_duration)
        seekbar.setProgress(start_point)

        //음원
        tv_up_load_cover_act_mp3_name.setText(upload_song_uri)

        iv_up_load_cover_act_confirm_btn.setOnClickListener {
            Log.e("er","StartPoint: " + start_point.toString() +"/songURI: " + upload_song_uri +"/picURI: " + selectedPicUri.toString())
            startActivity<UploadSongInfoActivity>("StartPoint" to start_point.toString(), "songURI" to upload_song_uri , "picURI" to selectedPicUri)
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

                    Log.e("Imgurl",selectedPicUri.toString())
                    //getRealPathFromURI(applicationContext, it.data)

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
