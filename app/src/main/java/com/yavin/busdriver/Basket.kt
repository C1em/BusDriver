package com.yavin.busdriver

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.ActionMenuView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.FragmentActivity

import org.json.JSONArray
import org.json.JSONException

class Basket(amv: ActionMenuView) {

    // store total as integer by making 12.34 € into 1234€
    private var total: Int = 0
    private var amvMenu: ActionMenuView = amv
    private var paymentId = 0

    private fun getTotal() : String {

        return "${(total / 100)}.${(total % 100)}"
    }

    fun addItem(itemPrice: Int) {

        Log.d("addItem", "itemPrice : $itemPrice")
        val totalMenuItem : MenuItem? = amvMenu.menu?.findItem(R.id.totalItem)
        Log.d("addItem", "total before: $total")
        total += itemPrice
        Log.d("addItem", "total after: $total")
        Log.d("addItem", "getTotal: ${getTotal()}")
        totalMenuItem?.title = "Total ${getTotal()}$ €"
    }
    fun empty() {

        total = 0
        val totalMenuItem : MenuItem? = amvMenu.menu?.findItem(R.id.totalItem)
        totalMenuItem?.title = "Total 0 €"
    }

    fun getPayIntent(): Intent {

        val paymentIntent = Intent()
        paymentIntent.component = ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
        paymentIntent.putExtra("vendorToken", "BusDriver")

        paymentIntent.putExtra("amount", getTotal())

        paymentIntent.putExtra("currency", "EUR")
        paymentIntent.putExtra("transactionType", "Debit")
        paymentIntent.putExtra("reference", paymentId++.toString())
        val jArray = JSONArray("[\"BusDriver\", \"payment of ${getTotal()}\"]")
        val receiptTicket = ArrayList<String>()
        for (i in 0 until jArray.length()) {
            try {
                receiptTicket.add(jArray.getString(i))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        paymentIntent.putExtra("receiptTicket", receiptTicket)
        return paymentIntent
    }
}