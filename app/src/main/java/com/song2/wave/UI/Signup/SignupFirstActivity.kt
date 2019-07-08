package com.song2.wave.UI.Signup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_signup_first.*
import android.text.Editable
import android.text.TextWatcher
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.bumptech.glide.Glide
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream


class SignupFirstActivity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data : Uri
    private var image : MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_first)

        edit_signup_act_nickname.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_verify_num.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_email.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_passwd.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        tv_signup_act_email_confirm.setOnClickListener {
            ll_signup_act_verify_num.visibility = View.VISIBLE
            tv_signup_act_email_confirm.visibility = View.GONE
        }

        tv_signup_act_verify_num_confirm.setOnClickListener {
            ll_signup_act_nickname.visibility = View.VISIBLE
            tv_signup_act_verify_num_confirm.visibility =View.GONE
        }

        tv_signup_act_nickname_confirm.setOnClickListener {
            ll_signup_act_passwd.visibility = View.VISIBLE
            tv_signup_act_nickname_confirm.visibility = View.GONE
        }

        rl_signup_act_profile.setOnClickListener{
            changeImage()
        }

        edit_signup_act_passwd.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btn_signup_act_next.visibility = View.VISIBLE
                // 입력되는 텍스트에 변화가 있을 때

            }
            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // 입력하기 전에

            }

        })



    }

    // 갤러리로부터 이미지 갖고올 때 사용하는 오버라이딩 메소드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    this.data = data!!.data
                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val img = File(getRealPathFromURI(applicationContext,this.data).toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    image = MultipartBody.Part.createFormData("image", img.name, photoBody)

                    // 선택한 이미지를 해당 이미지뷰에 적용
                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(img_signup_act_profile)

                } catch (e: Exception) {
                    e.printStackTrace()
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

    // 방 배경 이미지 변경
    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

}
