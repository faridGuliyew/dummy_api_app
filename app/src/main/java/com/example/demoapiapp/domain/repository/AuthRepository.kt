package com.example.demoapiapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(username : String, password : String) : String
    suspend fun register(username : String, password : String) : String

    fun getCurrentUserName() : String?

}