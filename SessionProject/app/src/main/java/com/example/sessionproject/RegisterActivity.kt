package com.example.sessionproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val button: Button = findViewById(R.id.button)
        val home_Btn : Button=findViewById(R.id.homeBtn)

        button.setOnClickListener{
            val TAG = "register_test"

            val userName = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val userEmail = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
            val userPassword = findViewById<EditText>(R.id.editTextTextPersonName3).text.toString()
            val userPassword2 = findViewById<EditText>(R.id.editTextTextPersonName4).text.toString()

            val user = UserRegister(
                name = userName,
                email = userEmail,
                password = userPassword,
                password2 = userPassword2
            )

            CoroutineScope(Dispatchers.IO).launch {
                val result = HttpApi.retrofitService.registerUser(user)
                Log.d(TAG, "result: $result")
            }
        }

        home_Btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
//
//        println("hello")
//
//
//        //val url = "http://192.168.10.43:3000/api/register"
//        val url = "http://192.168.10.43:3000"
//
//        val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
//
//        //val JSONObjectString = "{\"name\":\"Mahesh\"}" //The data I want to send
//
//        //var body:RequestBody = RequestBody.create(JSON, JSONObjectString)
//
//        val json = JSONObject()
//        json.put("name", "유민서")
//        json.put("password", "123456")
//        json.put("email", "msyou999@gmail.com")
//
//        val body = json.toString().toRequestBody(JSON)
//
//
//        val request = Request.Builder()
//            .post(body)
//            .url(url)
//            .build()
//
//        val client = OkHttpClient()
//
//        print(request)
//
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val tm = response.body?.string()
//                println(tm)
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                Log.d("Failed", "FAILED")
//                e.printStackTrace()
//            }
//        })

    }
}

