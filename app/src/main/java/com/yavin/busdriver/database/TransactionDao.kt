package com.yavin.busdriver.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yavin.busdriver.model.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getAll(): LiveData<List<Transaction>>

    @Insert
    suspend fun insertAll(vararg transactions: Transaction)
}