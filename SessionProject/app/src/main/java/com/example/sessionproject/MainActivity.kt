package com.example.sessionproject

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "login_test"

        val login_Btn: Button = findViewById(R.id.LoginBtn)
        val join_Btn: Button = findViewById(R.id.JoinBtn)
        val gotoProfile : Button = findViewById(R.id.button2)

        gotoProfile.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                var result: ProfileResponse? = null

                try {
                    result = HttpApi.retrofitService.getProfile()
                    Log.d(ContentValues.TAG, "result: $result")
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "session:${result}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        login_Btn.setOnClickListener {
            val userEmail = findViewById<EditText>(R.id.text_email).text.toString()
            val userPw = findViewById<EditText>(R.id.text_pw).text.toString()

            val user = UserLogin(
                email = userEmail,
                password = userPw
            )

            CoroutineScope(Dispatchers.IO).launch {
                val result = HttpApi.retrofitService.loginUser(user)
                Log.d(TAG, "result: $result")
            }

            val intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)

        }

        join_Btn.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

    }
}