package com.yavin.busdriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class TransactionsHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TransactionsHistory", "onCreate")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.transactions_history_activity)

        val transactionsHistory = findViewById<TextView>(R.id.transactionsHistory)

        val transactions = Transaction.getAll()
        val transactionsList: List<String> = transactions.map { transaction -> "id: ${transaction.first}, amount: ${getAmount(transaction.second)}" }
        transactionsHistory.text = transactionsList.joinToString("\n")
    }

    private fun getAmount(amount: Int): String {

        return "${(amount / 100)}.${(amount % 100)}"
    }
}