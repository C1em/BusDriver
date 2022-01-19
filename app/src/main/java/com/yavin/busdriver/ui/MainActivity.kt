package com.yavin.busdriver.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.yavin.busdriver.*
import org.json.JSONArray
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var amvMenu: ActionMenuView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val t = findViewById<View>(R.id.basket) as Toolbar
        amvMenu = t.findViewById<View>(R.id.amvMenu) as ActionMenuView
        amvMenu.setOnMenuItemClickListener { menuItem -> onOptionsItemSelected(menuItem) }

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container_view, MainFragment())
        ft.commit()

        setSupportActionBar(t)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, amvMenu.menu)
        viewModel.basket.observe(this,
            { basket ->
                amvMenu.menu.findItem(R.id.totalItem).title = "${basket.getTotalString()} €"
            })
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.basket.removeObservers(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: String = resources.getResourceName(item.itemId).split("/")[1]

        when (id) {

            "totalItem"-> {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure you want to empty basket ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->

                    viewModel.emptyBasket()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.show()
            }
            "payItem" -> {

                fun getPayIntent(): Intent {

                    val paymentIntent = Intent()
                    paymentIntent.component = ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
                    paymentIntent.putExtra("vendorToken", "BusDriver")

                    paymentIntent.putExtra("amount", viewModel.basket.value?.getTotalString())

                    paymentIntent.putExtra("currency", "EUR")
                    paymentIntent.putExtra("transactionType", "Debit")
                    val jArray = JSONArray("[\"BusDriver\", \"payment of ${viewModel.basket.value?.getTotalString()}\"]")
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

                //TODO: remove this
                viewModel.addTransaction(viewModel.basket.value?.getTotal()!!)

                startActivityForResult(getPayIntent(), 0)
            }
            "transactionsHistoryItem" -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container_view, TransactionsHistoryFragment())
                ft.addToBackStack(null)
                ft.commit()
            }
            "ticketPrice" -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container_view, TicketPriceFragment())
                ft.addToBackStack(null)
                ft.commit()
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {

                val bundle: Bundle = data!!.extras!!
                viewModel.addTransaction(bundle["amount"] as Int)
//                Transaction.add(bundle["transactionId"] as Int, bundle["amount"] as Int)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Payment success")
                val alert = builder.create()
                alert.show()
            }
            if (resultCode == RESULT_CANCELED) {

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Payment failed")
                val alert = builder.create()
                alert.show()
            }
        }
    } //onActivityResult

}