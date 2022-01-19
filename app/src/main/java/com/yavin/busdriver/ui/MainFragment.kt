package com.yavin.busdriver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yavin.busdriver.R
import com.yavin.busdriver.databinding.FragmentMainBinding


class MainFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMainBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_main, parent, false)
        binding.viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return binding.root
    }
}