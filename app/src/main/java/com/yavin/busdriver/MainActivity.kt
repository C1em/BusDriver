package com.yavin.busdriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import androidx.appcompat.widget.ActionMenuView

import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private lateinit var amvMenu: ActionMenuView
    private lateinit var basket :Basket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t = findViewById<View>(R.id.basket) as Toolbar
        amvMenu = t.findViewById<View>(R.id.amvMenu) as ActionMenuView
        amvMenu.setOnMenuItemClickListener { menuItem -> onOptionsItemSelected(menuItem) }
        basket = Basket(amvMenu)

        setSupportActionBar(t)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun addWeekTicket(v: View) {

        basket.addItem(1200)
    }

    fun addDayTicket(v: View) {

        basket.addItem(250)
    }

    fun addSingleJourneyTicket(v: View) {

        basket.addItem(110)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, amvMenu.menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: String = resources.getResourceName(item.itemId).split("/")[1]

        when (id) {

            "totalItem"-> {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure you want to empty basket ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->

                    basket.empty()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.show()
            }
            "payItem" -> startActivityForResult(basket.getPayIntent(), 0)
            "transactionsHistoryItem" -> {
                val transactionsHistoryActivity : Intent = Intent(this, TransactionsHistoryActivity::class.java)
                startActivity(transactionsHistoryActivity)
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.d("DEBUG", "ActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {

                basket.empty()
                val bundle: Bundle = data!!.extras!!
                Transaction.add(bundle["transactionId"] as Int, bundle["amount"] as Int)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Payment success")
                val alert = builder.create()
                alert.show()
            }
            if (resultCode == RESULT_CANCELED) {

                Log.d("onActivityResult", Transaction.getAll().toString())
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Payment failed")
                val alert = builder.create()
                alert.show()
            }
        }
    } //onActivityResult

}