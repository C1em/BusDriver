package com.yavin.busdriver.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yavin.busdriver.model.Basket
import com.yavin.busdriver.model.Price
import com.yavin.busdriver.model.Transaction
import com.yavin.busdriver.repository.TransactionRepository
import com.yavin.busdriver.utils.CustomMutableLiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _basket: CustomMutableLiveData<Basket> = CustomMutableLiveData(Basket())
    private val _sharedPreferences: SharedPreferences = application.getSharedPreferences("busDriver", Context.MODE_PRIVATE)
    private var _prices: MutableLiveData<Price> = MutableLiveData(Price(
        _sharedPreferences.getInt("PriceSingleJourneyTicket", 110),
        _sharedPreferences.getInt("PriceDayTicket", 250),
        _sharedPreferences.getInt("PriceWeekTicket", 1200)
    ))
    private val _isPriceValidated: MutableLiveData<Boolean> = MutableLiveData(false)
    var tmpPrices: Price = Price(_prices.value!!)
    private var transactionRepository: TransactionRepository = TransactionRepository(application)
    private var _allTransactions: List<Transaction> = listOf()

    val allTransactionsString: String
        get() = _allTransactions.joinToString("\n") { transaction ->
            "id: ${transaction.id}, amount: ${getAmount(transaction.amount)}"
        }

    init {
        transactionRepository.getAllTransactions().observeForever {

            _allTransactions = it
        }
    }

    val basket: LiveData<Basket>
        get() = _basket

    val prices: LiveData<Price>
        get() = _prices

    val isPriceValidated: LiveData<Boolean>
        get() = _isPriceValidated

    fun addSingleJourneyTicket() {

        _basket.value?.addItem(_prices.value!!.getSingleJourneyTicket())
    }

    fun addDayTicket() {

        _basket.value?.addItem(_prices.value!!.getDayTicket())
    }

    fun addWeekTicket() {

        _basket.value?.addItem(_prices.value!!.getWeekTicket())
    }

    fun emptyBasket() {

        basket.value?.empty()
    }

    fun validatePrices() {

        val editor = _sharedPreferences.edit()
        editor.putInt("PriceSingleJourneyTicket", tmpPrices.getSingleJourneyTicket())
        editor.putInt("PriceDayTicket", tmpPrices.getDayTicket())
        editor.putInt("PriceWeekTicket", tmpPrices.getWeekTicket())
        editor.apply()
        _prices.value = Price(tmpPrices)
        _isPriceValidated.value = true
    }

    fun resetPrices() {

        tmpPrices = Price(_prices.value!!)
        _isPriceValidated.value = false
    }

    @DelicateCoroutinesApi
    fun addTransaction(amount: Int) {

        GlobalScope.async {

            transactionRepository.insertAllTransactions(Transaction(0, amount))
        }
    }

    private fun getAmount(amount: Int): String {

        return "${(amount / 100)}.${(amount % 100)}"
    }
}