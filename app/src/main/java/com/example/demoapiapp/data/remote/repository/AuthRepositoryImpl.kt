package com.example.demoapiapp.data.remote.repository

import com.example.demoapiapp.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.Response
import retrofit2.http.POST
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth,
    private val firestore : FirebaseFirestore
) : AuthRepository {
    override suspend fun login(username: String, password: String): String {
        return suspendCoroutine { coroutine->
            auth.signInWithEmailAndPassword(username, password).addOnSuccessListener {
                coroutine.resume("Login Success")
            }.addOnFailureListener {
                coroutine.resume("Login Failed: ${it.message}")
            }
        }
    }

    override suspend fun register(username: String, password: String, email : String): String {
        return suspendCoroutine { coroutine->
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                firestore.collection("nicknames").document(email).set(
                    listOf(
                        FirebaseUser(
                            username,
                            email,
                            password
                        )
                    )
                )
                coroutine.resume("Register Success")
            }.addOnFailureListener {
                coroutine.resume("Register Failed: ${it.message}")
            }
        }
    }

    override fun getCurrentUserName(): String? {
        return auth.currentUser?.email?.split("@")?.first()
    }
}



data class FirebaseUser (
    val nickname : String = "",
    val email : String = "",
    val password : String = ""
)