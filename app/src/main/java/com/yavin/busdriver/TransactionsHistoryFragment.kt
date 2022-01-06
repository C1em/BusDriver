package com.yavin.busdriver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TransactionsHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v: View = inflater.inflate(R.layout.fragment_transactions_history, parent, false)
        val transactionsHistory = v.findViewById<TextView>(R.id.transactionsHistory)

        val transactions = Transaction.getAll()
        val transactionsList: List<String> = transactions.map { transaction -> "id: ${transaction.first}, amount: ${getAmount(transaction.second)}" }
        transactionsHistory.text = transactionsList.joinToString("\n")

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun getAmount(amount: Int): String {

        return "${(amount / 100)}.${(amount % 100)}"
    }
}