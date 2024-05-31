package com.example.demoapiapp.presentation.fragments.register_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.demoapiapp.R
import com.example.demoapiapp.base.BaseFragment
import com.example.demoapiapp.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    val viewModel by viewModels<RegisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonRegister.setOnClickListener { register() }

        binding.buttonLogin.setOnClickListener { login() }

        observe()

    }

    fun register() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        viewModel.register(username, password)
    }

    fun login() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        viewModel.login(username, password)
    }

    fun observe() {
        lifecycleScope.launch {
            viewModel.isLoading.collectLatest {
                binding.loading.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.message.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            viewModel.email.collectLatest {
                binding.textViewName.text = "Salam, $it!"
            }
        }
    }


}