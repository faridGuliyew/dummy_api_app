package com.example.demoapiapp.data.remote.repository

import com.example.demoapiapp.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import okhttp3.Response
import retrofit2.http.POST
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AuthApi {

    @POST("/login")
    suspend fun register() : Response
}
class AuthRepositoryImpl @Inject constructor(
    private val firebase : FirebaseAuth
) : AuthRepository {
    override suspend fun login(username: String, password: String): String {
        return suspendCoroutine { coroutine->
            firebase.signInWithEmailAndPassword(username, password).addOnSuccessListener {
                coroutine.resume("Login Success")
            }.addOnFailureListener {
                coroutine.resume("Login Failed: ${it.message}")
            }
        }
    }

    override suspend fun register(username: String, password: String): String {
        return suspendCoroutine { coroutine->
            firebase.createUserWithEmailAndPassword(username, password).addOnSuccessListener {
                coroutine.resume("Register Success")
            }.addOnFailureListener {
                coroutine.resume("Register Failed: ${it.message}")
            }
        }
    }

    override fun getCurrentUserName(): String? {
        return firebase.currentUser?.email?.split("@")?.first()
    }
}