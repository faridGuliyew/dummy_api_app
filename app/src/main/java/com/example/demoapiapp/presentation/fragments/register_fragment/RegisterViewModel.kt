package com.example.demoapiapp.presentation.fragments.register_fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapiapp.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class RegisterViewModel @Inject constructor (val authRepository: AuthRepository) : ViewModel() {


    val email = MutableStateFlow<String?>(null)

    val isLoading = MutableStateFlow(false)

    val message = MutableStateFlow<String?>(null)

    init {
        email.update { authRepository.getCurrentUserName() }
    }

    fun register(username : String, password : String, email : String) {
        viewModelScope.launch {
            isLoading.update { true }
            message.update { authRepository.register(username, password, email) }
            isLoading.update { false }
        }
    }

    fun login(username : String, password : String) {
        viewModelScope.launch {
            isLoading.update { true }
            message.update { authRepository.login(username, password) }
            isLoading.update { false }
        }
    }
}