package com.example.demoapiapp.presentation.fragments.test_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoapiapp.R
import com.example.demoapiapp.base.BaseFragment
import com.example.demoapiapp.databinding.FragmentTestBinding


class TestFragment : BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}