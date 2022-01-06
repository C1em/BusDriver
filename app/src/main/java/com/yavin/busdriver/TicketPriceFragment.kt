package com.yavin.busdriver

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

import android.view.ViewGroup

import android.view.LayoutInflater




class TicketPriceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_price, parent, false)
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    fun updateTicketPrice(v: View) {

        val inputSingleJourney = view?.findViewById<TextInputEditText>(R.id.inputSingleJourney)
        val inputDay = view?.findViewById<EditText>(R.id.inputDay)
        val inputWeek = view?.findViewById<EditText>(R.id.inputWeek)


        Log.d("updateTicketPrice", inputSingleJourney?.text.toString())
    }
}