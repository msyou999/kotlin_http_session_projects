package com.example.httpproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Button
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
//import okio.IOException
import java.io.IOException
import okhttp3.Response

import okhttp3.RequestBody
import java.net.URI.create


private val client = OkHttpClient()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button :Button = findViewById(R.id.button)

        button.setOnClickListener{
            setGetFun()
        }
    }
}




private fun requestPOST(){

    val url = "http://http://192.168.10.43:3000/api/register"

    val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

    //val JSONObjectString = "{\"name\":\"Mahesh\"}" //The data I want to send

    //var body:RequestBody = RequestBody.create(JSON, JSONObjectString)

    val json = JSONObject()
    json.put("name", "유민서")
    json.put("password", "123456")
    json.put("email", "msyou999@gmail.com")

    val body = json.toString().toRequestBody(JSON)


    val request = Request.Builder()
        .post(body)
        .url(url.toString())
        .build()

    val client = OkHttpClient()

    print(request)


    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: okhttp3.Call, response: Response) {
            val tm = response.body?.string()
            println(tm)
        }

        override fun onFailure(call: okhttp3.Call, e: IOException) {
            Log.d("Failed", "FAILED")
            e.printStackTrace()
        }
    })


}







private fun setGetFun() {

    val request = Request.Builder()
        //.url("http://192.168.10.79:8080/api")
        //.url("https://www.google.com")
        .url("http://192.168.10.43:3001/")
        .build()

    println("----------------------------------------------------------")

    client.newCall(request).enqueue(object : Callback {

        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
            println("fail")
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                /*
                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                 */
                println("RESPONSE BODY : "+response.body!!.string())
                println("REPONSE HEADER : "+response.headers!!.toString())
            }
        }

    })
}

