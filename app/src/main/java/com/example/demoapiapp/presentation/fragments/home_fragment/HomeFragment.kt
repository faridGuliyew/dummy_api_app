package com.example.demoapiapp.presentation.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demoapiapp.R
import com.example.demoapiapp.base.BaseFragment
import com.example.demoapiapp.databinding.FragmentHomeBinding
import com.example.demoapiapp.presentation.adapters.PopularMoviesAdapter
import com.example.demoapiapp.domain.model.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(bindingRoot = FragmentHomeBinding::inflate) {

    val viewModel by viewModels<HomeViewModel>()
    val adapter = PopularMoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setRv()

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetFragment)
        }
    }

    fun observe() {
        lifecycleScope.launch {
            viewModel.products
                .collect {
                    adapter.updateAdapter(it)
                }
        }
    }

    fun setRv() {
        binding.viewPager.adapter = adapter
    }
}