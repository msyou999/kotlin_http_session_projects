package com.example.sessionproject

import android.provider.ContactsContract


data class RegisterResponse(
    val success: Boolean = false
)


data class LoginResponse(
    val success: Boolean = false
)


data class ProfileResponse(
    val success: Boolean = false
)


data class LogoutResponse(
    val success: Boolean
)


data class UserLogin(
    val email: String,
    val password: String
)


data class UserRegister(
    val name: String,
    val email: String,
    val password: String,
    val password2: String
)

