package com.example.demoapiapp.domain.repository

interface AuthenticationRepository {

    suspend fun login()
    suspend fun logout()

}