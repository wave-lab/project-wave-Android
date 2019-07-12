package com.song2.wave.UI.Main.MyPage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_acoustic
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_ballade
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_calm
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_dance
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_dreamy
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_etc
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_exciting
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_groovy
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_hip
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_hiphop
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_mournful
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_pop
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_randbsoul
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_rock
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_romanic
import kotlinx.android.synthetic.main.activity_mypage_artist_setting.btn_profile_edit_sad
import kotlinx.android.synthetic.main.activity_mypage_setting.*

class MyPageProfileSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_setting)
        //장르 선택시 색변경
        btn_profile_edit_ballade.setOnClickListener {
            btn_profile_edit_ballade.isSelected = !btn_profile_edit_ballade.isSelected
        }
        btn_profile_edit_pop.setOnClickListener {
            btn_profile_edit_pop.isSelected = !btn_profile_edit_pop.isSelected
        }
        btn_profile_edit_acoustic.setOnClickListener {
            btn_profile_edit_acoustic.isSelected = !btn_profile_edit_acoustic.isSelected
        }
        btn_profile_edit_dance.setOnClickListener {
            btn_profile_edit_dance.isSelected = !btn_profile_edit_dance.isSelected
        }
        btn_profile_edit_hiphop.setOnClickListener {
            btn_profile_edit_hiphop.isSelected = !btn_profile_edit_hiphop.isSelected
        }
        btn_profile_edit_randbsoul.setOnClickListener {
            btn_profile_edit_randbsoul.isSelected = !btn_profile_edit_randbsoul.isSelected
        }
        btn_profile_edit_rock.setOnClickListener {
            btn_profile_edit_rock.isSelected= !btn_profile_edit_rock.isSelected
        }
        btn_profile_edit_etc.setOnClickListener {
            btn_profile_edit_etc.isSelected= !btn_profile_edit_etc.isSelected
        }


        //무드 선택시 색변경
        btn_profile_edit_exciting.setOnClickListener {
            btn_profile_edit_exciting.isSelected = !btn_profile_edit_exciting.isSelected
        }
        btn_profile_edit_mournful.setOnClickListener {
            btn_profile_edit_mournful.isSelected = !btn_profile_edit_mournful.isSelected
        }
        btn_profile_edit_romanic.setOnClickListener {
            btn_profile_edit_romanic.isSelected = !btn_profile_edit_romanic.isSelected
        }
        btn_profile_edit_sad.setOnClickListener {
            btn_profile_edit_sad.isSelected = !btn_profile_edit_sad.isSelected
        }
        btn_profile_edit_hip.setOnClickListener {
            btn_profile_edit_hip.isSelected = !btn_profile_edit_hip.isSelected
        }
        btn_profile_edit_calm.setOnClickListener {
            btn_profile_edit_calm.isSelected = !btn_profile_edit_calm.isSelected
        }
        btn_profile_edit_groovy.setOnClickListener {
            btn_profile_edit_groovy.isSelected= !btn_profile_edit_groovy.isSelected
        }
        btn_profile_edit_dreamy.setOnClickListener {
            btn_profile_edit_dreamy.isSelected= !btn_profile_edit_dreamy.isSelected
        }
//카메라 버튼 갤러리 불러오기
        val REQUEST_CODE_SELECT_IMAGE : Int = 1004

        cv_edit_frag_profile_photo.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE)
        }

    }
}