package com.example.sessionproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val logout_Btn: Button = findViewById(R.id.LogoutBtn)
        val session_Btn: Button = findViewById(R.id.SessionBtn)

        logout_Btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = HttpApi.retrofitService.logoutUser()
                Log.d(TAG, "result: $result")
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        session_Btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                var result: ProfileResponse? = null

                try {
                    result = HttpApi.retrofitService.getProfile()
                    Log.d(TAG, "result: $result")
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LogoutActivity, "result:${result}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}