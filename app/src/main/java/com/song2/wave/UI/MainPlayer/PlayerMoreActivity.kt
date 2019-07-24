package com.song2.wave.UI.MainPlayer

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_library_more.*
import kotlinx.android.synthetic.main.dialog_report.*

class PlayerMoreActivity : Activity() {

    lateinit var songId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_more)

        var requestManager = Glide.with(this)

        // 노래 id값 저장
        songId = intent.getStringExtra("songId")

        // 전달 받은 값으로 화면 구성
        tv_library_more_act_title.text = intent.getStringExtra("title") + " - " + intent.getStringExtra("originAtist")
        tv_library_more_act_artist.text = intent.getStringExtra("coverArtist")
        requestManager.load(intent.getStringExtra("imgUrl")).into(iv_library_more_act_image)

        // 플레이리스트에 추가 버튼 클릭시
        ll_library_more_act_play_list.setOnClickListener {
            Toast.makeText(applicationContext, "플레이리스트에 추가 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        // 아티스트 프로필 추가 버튼 클릭시
        ll_library_more_act_artist_profile.setOnClickListener {
            Toast.makeText(applicationContext, "아티스트 프로필 추가 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        // 삭제 버튼 클릭시
        ll_library_more_act_delete.setOnClickListener {
            Toast.makeText(applicationContext, "삭제 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        // 신고 버튼 클릭시
        ll_library_more_act_notify.setOnClickListener {
            showReportDialog()
        }

        // 취소 버튼 클릭시
        bt_library_more_act_cancel.setOnClickListener {
            onBackPressed() // 뒤로 가기
        }

        // 좋아요 버튼 클릭시
        iv_library_more_act_like.setOnClickListener {
            iv_library_more_act_like.isSelected = !iv_library_more_act_like.isSelected
        }

        // 공유 버튼 클릭시
        iv_library_more_act_share.setOnClickListener {
            Toast.makeText(applicationContext, "공유 버튼 클릭", Toast.LENGTH_LONG).show()
        }

    }

    // 신고 커스텀 다이얼로그 메서드
    protected fun showReportDialog() {
        var reportDialog = Dialog(this)
        reportDialog.setCancelable(true)
        reportDialog.getWindow().setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        val exitDialogView = this!!.layoutInflater.inflate(R.layout.dialog_report, null)
        reportDialog.setContentView(exitDialogView)

        // 신고 완료 버튼 클릭 시
        reportDialog.tv_report_dialog_confirm.setOnClickListener {
            Toast.makeText(applicationContext, "신고 완료", Toast.LENGTH_LONG).show()
            reportDialog.dismiss()
        }

        // 신고 취소 버튼 클릭 시
        reportDialog.tv_report_dialog_cancel.setOnClickListener {
            Toast.makeText(applicationContext, "취소", Toast.LENGTH_LONG).show()
            reportDialog.dismiss()
        }
        reportDialog.show()
    }
}
