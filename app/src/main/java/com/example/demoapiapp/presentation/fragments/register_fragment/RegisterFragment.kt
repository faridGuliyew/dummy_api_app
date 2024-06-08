package com.example.demoapiapp.presentation.fragments.register_fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
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
import com.example.demoapiapp.data.remote.repository.FirebaseUser
import com.example.demoapiapp.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    val viewModel by viewModels<RegisterViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = "Someone"
        binding.textViewTitle.text =
            Html.fromHtml("Hi, <font color = \"0x3225FF\">$username</font>!")


        val email = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val nickname = binding.editTextNickname.text.toString()

        viewModel.register(nickname, password, email)


        fun login() {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            viewModel.login(username, password)
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest {
                binding.loading.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.message.filterNotNull().collectLatest { message->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            viewModel.email.collectLatest {
                binding.textViewName.text = "Salam, $it!"
            }
        }

    }

    //
//    fun register() {
//        val email = binding.editTextUsername.text.toString()
//        val password = binding.editTextPassword.text.toString()
//        val nickname = binding.editTextNickname.text.toString()
//
//        viewModel.register(nickname, password, email)
//    }
//
    fun login() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        viewModel.login(username, password)
    }
//
//    fun observe() {
//        lifecycleScope.launch {
//            viewModel.isLoading.collectLatest {
//                binding.loading.isVisible = it
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.message.collectLatest {
//                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.email.collectLatest {
//                binding.textViewName.text = "Salam, $it!"
//            }
//        }
//    }
//

}

data class PopularMovies(
    val movies: List<Images>,
)

data class Images(
    val image1: Int,
    val image2: Int,
    val image3: Int,
)