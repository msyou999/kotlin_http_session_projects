package com.example.sessionproject

import android.media.session.MediaSession
import com.google.gson.JsonObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.CookieJar
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.internal.cookieToString
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.CookieManager

private const val BASE_URL =
    "http://192.168.10.43:3000/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient.Builder()
    .cookieJar(JavaNetCookieJar(CookieManager()))
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface HttpApiService {
    @POST("register")
    suspend fun registerUser(@Body userInfo: UserRegister): RegisterResponse

    @POST("login")
    suspend fun loginUser(@Body userInfo: UserLogin): LoginResponse

    @GET("profile")
    suspend fun getProfile(): ProfileResponse

    @GET("logout")
    suspend fun logoutUser(): LogoutResponse
}


object HttpApi {
    val retrofitService: HttpApiService by lazy { retrofit.create(HttpApiService::class.java) }
}