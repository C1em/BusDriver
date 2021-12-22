package com.yavin.busdriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

class TicketPrice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_price)
    }

    fun updateTicketPrice(v: View) {

        val inputSingleJourney = findViewById<TextInputEditText>(R.id.inputSingleJourney)
        val inputDay = findViewById<EditText>(R.id.inputDay)
        val inputWeek = findViewById<EditText>(R.id.inputWeek)


        Log.d("updateTicketPrice", inputSingleJourney?.text.toString())
    }
}