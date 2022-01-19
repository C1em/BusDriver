package com.yavin.busdriver.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yavin.busdriver.R
import com.yavin.busdriver.databinding.FragmentTicketPriceBinding


class TicketPriceFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, parent, savedInstanceState)

        val binding: FragmentTicketPriceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ticket_price, parent, false)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // return to mainActivity on price changed
        viewModel.isPriceValidated.observe(viewLifecycleOwner, {

            if (it == true)
                activity?.supportFragmentManager?.popBackStack()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.resetPrices()
    }
}