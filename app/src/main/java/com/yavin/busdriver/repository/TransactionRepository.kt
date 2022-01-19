package com.yavin.busdriver.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.yavin.busdriver.database.AppDatabase
import com.yavin.busdriver.database.TransactionDao
import com.yavin.busdriver.model.Transaction


class TransactionRepository(application: Application) {

    private var transactionDao: TransactionDao

    init {
        val database = AppDatabase(application)
        transactionDao = database.transactionDao()
    }

    fun getAllTransactions(): LiveData<List<Transaction>> {

        return transactionDao.getAll()
    }

    suspend fun insertAllTransactions(vararg transactions: Transaction) {

        transactionDao.insertAll(*transactions)
    }
}