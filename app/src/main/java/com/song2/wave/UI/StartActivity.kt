package com.song2.wave.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.R
import com.song2.wave.UI.Login.LoginActivity
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.UI.Signup.SignupFirstActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        tv_start_act_login_btn.setOnClickListener {
            var intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        bt_start_act_email_login.setOnClickListener {
            var intent = Intent(applicationContext, SignupFirstActivity::class.java)
            startActivity(intent)
        }
        bt_start_act_scan_wave.setOnClickListener {
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
