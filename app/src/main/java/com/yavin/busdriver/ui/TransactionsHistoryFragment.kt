package com.yavin.busdriver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yavin.busdriver.R
import com.yavin.busdriver.databinding.FragmentTransactionsHistoryBinding

class TransactionsHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, parent, savedInstanceState)

        val binding: FragmentTransactionsHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transactions_history, parent, false)

        binding.viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return binding.root
    }
}