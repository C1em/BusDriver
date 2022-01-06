package com.yavin.busdriver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment




class MainFragment(private val basket: Basket) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_main, parent, false)

        var button: Button = v.findViewById(R.id.button_ticket1) as Button
        button.setOnClickListener {

            addSingleJourneyTicket()
        }
        button = v.findViewById(R.id.button_ticket2) as Button
        button.setOnClickListener {

            addDayTicket()
        }
        button = v.findViewById(R.id.button_ticket3) as Button
        button.setOnClickListener {

            addWeekTicket()
        }

        return v
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private fun addWeekTicket() {

        basket.addItem(1200)
    }

    private fun addDayTicket() {

        basket.addItem(250)
    }

    private fun addSingleJourneyTicket() {

        basket.addItem(110)
    }

}